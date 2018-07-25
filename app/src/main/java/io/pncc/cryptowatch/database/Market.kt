package io.pncc.cryptowatch.database

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Market(@SerializedName("data") var data: Map<Int, Coin>): Serializable{
    data class Coin(
            @SerializedName("id") var id: Int,
            @SerializedName("name") var name: String,
            @SerializedName("symbol") var symbol: String,
            @SerializedName("website_slug") var websiteSlug: String,
            @SerializedName("rank") var rank: Int,
            @SerializedName("circulating_supply") var circulatingSupply: Double,
            @SerializedName("total_supply") var totalSupply: Double,
            @SerializedName("max_supply") var maxSupply: Double,
            @SerializedName("quotes") var quotes: Map<String, Quotes>,
            @SerializedName("last_updated") var lastUpdated: Int
    )

    data class Quotes(
            @SerializedName("price") var price: Double,
            @SerializedName("volume_24h") var volume24h: Double,
            @SerializedName("market_cap") var marketCap: Double,
            @SerializedName("percent_change_1h") var percentChange1h: Double,
            @SerializedName("percent_change_24h") var percentChange24h: Double,
            @SerializedName("percent_change_7d") var percentChange7d: Double
    )
}