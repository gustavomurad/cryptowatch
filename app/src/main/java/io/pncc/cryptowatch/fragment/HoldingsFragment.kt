package io.pncc.cryptowatch.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
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
import io.pncc.cryptowatch.viewmodels.HoldingsListViewModel

class HoldingsFragment : Fragment() {
    private lateinit var adapter: HoldingsAdapter
    private var mHoldingsViewModel: HoldingsListViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mHoldingsViewModel = ViewModelProviders.of(this).get(HoldingsListViewModel::class.java!!)

        mHoldingsViewModel!!.getHoldings().observe(this, object: Observer<List<Holdings>> {
            override fun onChanged(holdings: List<Holdings>?) {
                // Update the cached copy of the words in the adapter.
                adapter.setHoldings(ArrayList(holdings))
            }
        })

        return inflater.inflate(R.layout.tab_holdings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView: RecyclerView = view.findViewById(R.id.recyclerViewHoldings)

        getMarketCapData(mRecyclerView)
    }



    private fun getMarketCapData(mRecyclerView: RecyclerView) {
        var holdings: ArrayList<Holdings> = arrayListOf()

        //holdings.add(Holdings(1, "PNCC Coin", "BTC", 2500.01, 22.5, 0.5, 1500.00, Date(1L)))

        context?.let {
            adapter = HoldingsAdapter(it)
        }

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

    }

}