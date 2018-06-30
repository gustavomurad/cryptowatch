package io.pncc.cryptowatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.gson.GsonBuilder
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.TextHttpResponseHandler
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CryptocurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerViewCryptocurrency)

        getMarketCapData(mRecyclerView)

        btn_refresh.setOnClickListener{
            getMarketCapData(mRecyclerView)
        }

        cryptoSwipeRefresh.setOnRefreshListener {
            getMarketCapData(mRecyclerView)
        }
    }

    private fun getMarketCapData(mRecyclerView: RecyclerView) {
        val client = AsyncHttpClient()
        client.get(Constants.API_URI, object : TextHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: String) {

                val gson = GsonBuilder().create()
                val crypto = gson.fromJson(response, Cryptocurrency::class.java)

                adapter = CryptocurrencyAdapter(this@MainActivity, crypto.data)
                mRecyclerView.setHasFixedSize(true)
                mRecyclerView.adapter = adapter
                mRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                cryptoSwipeRefresh.isRefreshing = false

                Log.i("response: ", response)
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Log.e("response: ", throwable.toString())
            }
        })
    }
}
