package io.pncc.cryptowatch.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.pncc.cryptowatch.R
import io.pncc.cryptowatch.adapter.HoldingsAdapter
import io.pncc.cryptowatch.model.Holdings

class HoldingsFragment : Fragment() {
    private lateinit var adapter: HoldingsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.tab_holdings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView: RecyclerView = view.findViewById(R.id.recyclerViewHoldings)

        getMarketCapData(mRecyclerView)
    }

    private fun getMarketCapData(mRecyclerView: RecyclerView) {
        var holdings: ArrayList<Holdings> = arrayListOf()

        holdings.add(Holdings(1, "PNCC Coin", "BTC", 2500.01, 22.5))

        context?.let {
            adapter = HoldingsAdapter(it, holdings)
        }

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

    }

}