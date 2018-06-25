package io.pncc.cryptowatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

class CryptocurrencyAdapter: RecyclerView.Adapter<CryptocurrencyAdapter.MyViewHolder> {
    private var mLayoutInflater: LayoutInflater? = null
    private var mCryptocurrency: ArrayList<Cryptocurrency.Coin> = arrayListOf()

    constructor(mContext: Context, data: ArrayList<Cryptocurrency.Coin>) {
        this.mLayoutInflater = LayoutInflater.from(mContext)
        this.mCryptocurrency = data
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

        holder.name.let {
            it.text = crypto.name
        }
        holder.price.let {
            it.text = "${formatMoney.format(crypto.quotes["USD"]?.price)} (${String.format("%.2f%%",crypto.quotes["USD"]?.percentChange24h)})"
        }
        holder.percentChange24h.let {
            it.text = String.format("%.0f%%",crypto.quotes["USD"]?.percentChange24h)
        }
        holder.volume24h.let {
            it.text = formatMoney.format(crypto.quotes["USD"]?.volume24h)
        }
    }

    inner class MyViewHolder: RecyclerView.ViewHolder {
        var name:  TextView
        var price: TextView
        var percentChange24h:  TextView
        var volume24h: TextView

        constructor(itemView: View) : super(itemView) {
            this.name = itemView.findViewById(R.id.tv_name)
            this.price = itemView.findViewById(R.id.tv_price)
            this.percentChange24h = itemView.findViewById(R.id.tv_change_24h)
            this.volume24h = itemView.findViewById(R.id.tv_volume_24h)
        }
    }
}
