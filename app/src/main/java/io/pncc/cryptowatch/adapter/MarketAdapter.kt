package io.pncc.cryptowatch.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.pncc.cryptowatch.R
import io.pncc.cryptowatch.constants.Constants
import io.pncc.cryptowatch.model.Market
import java.text.NumberFormat
import java.util.*

class MarketAdapter: RecyclerView.Adapter<MarketAdapter.MyViewHolder> {
    private var mLayoutInflater: LayoutInflater? = null
    private var mMarket: ArrayList<Market.Coin> = arrayListOf()
    private var mContext: Context


    constructor(mContext: Context, data: ArrayList<Market.Coin>) {
        this.mLayoutInflater = LayoutInflater.from(mContext)
        this.mMarket = data
        this.mContext = mContext
    }

    override fun getItemCount(): Int {
        return mMarket.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = mLayoutInflater!!.inflate(R.layout.tab_market_fragment_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
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
            Picasso.with(mContext).load(Constants.GRAPH_URI.format(crypto.id)).into(it)
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
            textView.setTextColor(mContext.getColor(R.color.abc_secondary_text_material_light))
            "\u25B2"
        } else{
            textView.setTextColor(mContext.getColor(R.color.colorTextNegativeValue))
            "\u25BC"
        }

        val percentText = "$arrow ${String.format("%.02f%%", percent)} $label"
        textView.text = percentText
    }

    inner class MyViewHolder: RecyclerView.ViewHolder {
        var name:  TextView
        var price: TextView
        var percentChange1h:  TextView
        var percentChange24h: TextView
        var percentChange7d: TextView
        var volume24h: TextView
        var icon: ImageView
        var coinGraph: ImageView

        constructor(itemView: View) : super(itemView) {
            this.name = itemView.findViewById(R.id.tv_name)
            this.price = itemView.findViewById(R.id.tv_price)
            this.percentChange1h = itemView.findViewById(R.id.tv_change_1h)
            this.percentChange24h = itemView.findViewById(R.id.tv_change_24h)
            this.percentChange7d = itemView.findViewById(R.id.tv_change_7d)
            this.volume24h = itemView.findViewById(R.id.tv_volume_24h)
            this.icon = itemView.findViewById(R.id.cryptoIcon)
            this.coinGraph = itemView.findViewById(R.id.coinGraph)
        }
    }
}
