package io.pncc.cryptowatch.service

import io.pncc.cryptowatch.database.Market
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MarketService{
    @GET("ticker/")
    fun getMarketData(@Query("limit") limit: Int): Call<Market>
}