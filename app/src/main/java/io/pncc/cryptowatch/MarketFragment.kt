package io.pncc.cryptowatch

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.pncc.cryptowatch.adapters.MarketAdapter
import io.pncc.cryptowatch.utilities.InjectorUtils
import io.pncc.cryptowatch.viewmodels.MarketListViewModel
import kotlinx.android.synthetic.main.tab_market_fragment.*


class MarketFragment : Fragment() {
    private lateinit var mMarketViewModel: MarketListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.tab_market_fragment, container, false)
        val factory = InjectorUtils.provideMarketViewModelFactory()
        val context = context ?: return view

        mMarketViewModel = ViewModelProviders.of(this, factory).get(MarketListViewModel::class.java)

        val adapter = MarketAdapter(context)
        view.findViewById<RecyclerView>(R.id.recyclerViewMarket).adapter = adapter
        view.findViewById<RecyclerView>(R.id.recyclerViewMarket).layoutManager = LinearLayoutManager(context)
        subscribeUi(adapter)

        return view
    }

    private fun subscribeUi(adapter: MarketAdapter) {
        mMarketViewModel.getMarketData().observe(viewLifecycleOwner, Observer { market ->
            if (market != null) adapter.setMarkets(market)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cryptoSwipeRefresh.setOnRefreshListener {
            mMarketViewModel.refreshMarketData()
            cryptoSwipeRefresh.isRefreshing = false
        }
    }
}