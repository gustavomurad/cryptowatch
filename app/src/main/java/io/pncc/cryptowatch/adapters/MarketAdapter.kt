package io.pncc.cryptowatch.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.pncc.cryptowatch.R
import io.pncc.cryptowatch.constants.Constants
import io.pncc.cryptowatch.database.Holdings
import io.pncc.cryptowatch.database.Market
import java.text.NumberFormat
import java.util.*

class MarketAdapter(private var mContext: Context): RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    private var mMarket: ArrayList<Market.Coin> = arrayListOf()

    override fun getItemCount(): Int {
        return mMarket.size
    }

    fun setMarkets(market: Market){
        this.mMarket = ArrayList(market.data.values)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        val view = mLayoutInflater.inflate(R.layout.tab_market_fragment_row, parent, false)
        return MarketViewHolder(view)
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        val crypto: Market.Coin= mMarket[position]
        val formatMoney = NumberFormat.getCurrencyInstance(Locale.US)

        holder.icon.let {
            try {
                val imageResource: Int = mContext.resources.getIdentifier(Constants.RESOURCE_URI.format(crypto.symbol.toLowerCase()), null, mContext.packageName )
                val res = mContext.getDrawable(imageResource)
                it.setImageDrawable(res)
            }catch (e: Exception){
            Log.e("Fail to load icon: ","ic_${crypto.symbol.toLowerCase()}.xml" )
            }
        }

        holder.coinGraph.let{
            Glide.with(mContext)
                    .load(Constants.GRAPH_URI.format(crypto.id))
                    .into(it)
        }


        holder.name.let {
            val name = "${crypto.name} (${crypto.symbol})"
            it.text = name
        }

        holder.price.let {
            val price = "${mContext.getString(R.string.label_price)}: ${formatMoney.format(crypto.quotes["USD"]?.price)}"
            it.text = price
        }

        holder.percentChange1h.let {
            setPercentChange(crypto.quotes["USD"]?.percentChange1h ?: 0.0, it, mContext.getString(R.string.label_change_small_1h))
        }

        holder.percentChange24h.let {
            setPercentChange(crypto.quotes["USD"]?.percentChange24h ?: 0.0, it, mContext.getString(R.string.label_change_small_24h))
        }

        holder.percentChange7d.let {
            setPercentChange(crypto.quotes["USD"]?.percentChange7d ?: 0.0, it, mContext.getString(R.string.label_change_small_7d))
        }

        holder.volume24h.let {
            val volume = "${mContext.getString(R.string.label_volume_24h)}: ${formatMoney.format(crypto.quotes["USD"]?.volume24h)}"
            it.text = volume
        }
    }

    private fun setPercentChange(percent: Double, textView: TextView, label: String) {
        val arrow = if (percent >= 0){
            textView.setTextColor(mContext.getColor(R.color.secondaryTextColor))
            "\u25B2"
        } else{
            textView.setTextColor(mContext.getColor(R.color.colorTextNegativeValue))
            "\u25BC"
        }

        val percentText = "$arrow ${String.format("%.02f%%", percent)} $label"
        textView.text = percentText
    }

    inner class MarketViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var price: TextView = itemView.findViewById(R.id.tv_price)
        var percentChange1h: TextView = itemView.findViewById(R.id.tv_change_1h)
        var percentChange24h: TextView = itemView.findViewById(R.id.tv_change_24h)
        var percentChange7d: TextView = itemView.findViewById(R.id.tv_change_7d)
        var volume24h: TextView = itemView.findViewById(R.id.tv_volume_24h)
        var icon: ImageView = itemView.findViewById(R.id.cryptoIcon)
        var coinGraph: ImageView = itemView.findViewById(R.id.coinGraph)
    }
}
