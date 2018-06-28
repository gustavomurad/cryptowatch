package io.pncc.cryptowatch

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

class CryptocurrencyAdapter: RecyclerView.Adapter<CryptocurrencyAdapter.MyViewHolder> {
    private var mLayoutInflater: LayoutInflater? = null
    private var mCryptocurrency: ArrayList<Cryptocurrency.Coin> = arrayListOf()
    private var mContext: Context

    constructor(mContext: Context, data: ArrayList<Cryptocurrency.Coin>) {
        this.mLayoutInflater = LayoutInflater.from(mContext)
        this.mCryptocurrency = data
        this.mContext = mContext
    }

    override fun getItemCount(): Int {
        return mCryptocurrency.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = mLayoutInflater!!.inflate(R.layout.activity_main_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var crypto: Cryptocurrency.Coin= mCryptocurrency[position]
        val formatMoney = NumberFormat.getCurrencyInstance(Locale.US)

        holder.icon.let {
            val imageResource: Int = mContext.resources.getIdentifier("@drawable/ic_${crypto.symbol.toLowerCase()}", null, mContext.packageName )
            if (imageResource != 0){
                val res = mContext.resources.getDrawable(imageResource)
                it.setImageDrawable(res)
            }else{
                Log.i("Fail to load icon: ","ic_${crypto.symbol.toLowerCase()}" )
            }
        }

        holder.name.let {
            it.text = "${crypto.name} (${crypto.symbol})"
        }
        holder.price.let {
            it.text = "${mContext.getString(R.string.label_price)}: ${formatMoney.format(crypto.quotes["USD"]?.price)}"
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
            it.text =  "${mContext.getString(R.string.label_volume_24h)}: ${formatMoney.format(crypto.quotes["USD"]?.volume24h)}"
        }
    }

    private fun setPercentChange(percent: Double, textView: TextView, label: String) {
        var arrow: String = ""
        if (percent >= 0){
            textView.setTextColor(Color.BLACK)
            arrow = "\uD83D\uDD3A"
        } else{
            textView.setTextColor(Color.RED)
            arrow = "\uD83D\uDD3B"
        }

        textView.text = "$arrow ${String.format("%.02f%%", percent)} $label"
    }

    inner class MyViewHolder: RecyclerView.ViewHolder {
        var name:  TextView
        var price: TextView
        var percentChange1h:  TextView
        var percentChange24h: TextView
        var percentChange7d: TextView
        var volume24h: TextView
        var icon: ImageView

        constructor(itemView: View) : super(itemView) {
            this.name = itemView.findViewById(R.id.tv_name)
            this.price = itemView.findViewById(R.id.tv_price)
            this.percentChange1h = itemView.findViewById(R.id.tv_change_1h)
            this.percentChange24h = itemView.findViewById(R.id.tv_change_24h)
            this.percentChange7d = itemView.findViewById(R.id.tv_change_7d)
            this.volume24h = itemView.findViewById(R.id.tv_volume_24h)
            this.icon = itemView.findViewById(R.id.cryptoIcon)
        }
    }
}
