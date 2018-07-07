package io.pncc.cryptowatch.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import io.pncc.cryptowatch.R
import io.pncc.cryptowatch.constants.Constants
import io.pncc.cryptowatch.database.Holdings
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class HoldingsAdapter(private val mContext: Context): RecyclerView.Adapter<HoldingsAdapter.HoldingsMyViewHolder>() {
    private var mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)
    private var mMarket: ArrayList<Holdings> = arrayListOf()

    fun setHoldings(holdings: List<Holdings>){
        this.mMarket = ArrayList(holdings)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mMarket.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoldingsMyViewHolder {
        val view = mLayoutInflater.inflate(R.layout.tab_holdings_fragment_row, parent, false)
        return HoldingsMyViewHolder(view)
    }

    override fun onBindViewHolder(holder: HoldingsMyViewHolder, position: Int) {
        val crypto: Holdings = mMarket[position]
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

        holder.name.let {
            val name = "${crypto.name} (${crypto.symbol})"
            it.text = name
        }

        holder.price.let {
            val price = "${mContext.getString(R.string.label_price)}: ${formatMoney.format(crypto.price)}"
            it.text = price
        }
    }

    inner class HoldingsMyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var name:  TextView = itemView.findViewById(R.id.coinName)
        var price: TextView = itemView.findViewById(R.id.price)
        var icon: ImageView = itemView.findViewById(R.id.coinIcon)
    }
}
