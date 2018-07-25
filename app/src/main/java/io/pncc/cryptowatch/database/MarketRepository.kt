package io.pncc.cryptowatch.database

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.pncc.cryptowatch.constants.Constants
import io.pncc.cryptowatch.service.MarketService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MarketRepository private constructor(private val marketService: MarketService){
    fun getMarketData(): LiveData<Market> {
        val data = MutableLiveData<Market>()
        marketService.getMarketData(100).enqueue(object: Callback<Market> {
            override fun onResponse(call: Call<Market>?, response: Response<Market>?) {
                data.value = response?.body()
            }

            override fun onFailure(call: Call<Market>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        return data
    }

    companion object {
        @Volatile private var instance: MarketRepository? = null

        fun getInstance() =
                instance ?: synchronized(this) {
                    val retrofit = Retrofit.Builder()
                            .baseUrl(Constants.API_URI)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                    instance ?: MarketRepository(retrofit.create(MarketService::class.java)).also { instance = it }
                }
    }

}