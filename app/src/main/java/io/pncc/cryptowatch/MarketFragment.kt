package io.pncc.cryptowatch

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import io.pncc.cryptowatch.adapters.MarketAdapter
import io.pncc.cryptowatch.constants.Constants
import io.pncc.cryptowatch.database.Market
import kotlinx.android.synthetic.main.tab_market_fragment.*

class MarketFragment : Fragment() {
    private lateinit var adapter: MarketAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.tab_market_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mRecyclerView: RecyclerView = view.findViewById(R.id.recyclerViewMarket)

        getMarketCapData(mRecyclerView)

        cryptoSwipeRefresh.setOnRefreshListener {
            getMarketCapData(mRecyclerView)
        }
    }

    private fun getMarketCapData(mRecyclerView: RecyclerView) {
        val client = AsyncHttpClient()
        client.get(Constants.API_URI, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: String) {

                val gson = GsonBuilder().create()
                val crypto = gson.fromJson(response, Market::class.java)

                context?.let {
                    adapter = MarketAdapter(it, ArrayList(crypto.data.values))
                }

                mRecyclerView.setHasFixedSize(true)
                mRecyclerView.adapter = adapter
                mRecyclerView.layoutManager = LinearLayoutManager(context)

                cryptoSwipeRefresh.isRefreshing = false

                Log.i("response: ", response)
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Log.e("response: ", throwable.toString())
            }
        })
    }
}