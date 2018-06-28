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


class MainActivity : AppCompatActivity() {
    private val mUrl: String = "https://api.coinmarketcap.com/v2/ticker/?limit=20"
    private lateinit var adapter: CryptocurrencyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView: RecyclerView = findViewById(R.id.recyclerViewCryptocurrency)

        val client = AsyncHttpClient()

        client.get(mUrl, object:TextHttpResponseHandler() {
            override fun onSuccess(statusCode:Int, headers:Array<Header>, response:String) {

                val gson = GsonBuilder().create()
                val crypto = gson.fromJson(response, Cryptocurrency::class.java)

                adapter = CryptocurrencyAdapter(this@MainActivity, ArrayList(crypto.data.values))
                mRecyclerView.setHasFixedSize(true)
                mRecyclerView.adapter = adapter
                mRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                Log.i("response: ", response)
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseString: String?, throwable: Throwable?) {
                Log.e("response: ", throwable.toString())
            }
        })
    }
}
