package io.pncc.cryptowatch

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Cryptocurrency: Serializable{
    @SerializedName("data") var data: Map<Int,Coin>
    @SerializedName("metadata") var metadata: Metadata

    constructor(data: Map<Int,Coin>, metadata: Metadata) {
        this.data = data
        this.metadata = metadata
    }

    data class Metadata(
            @SerializedName("timestamp") var timestamp: Int,
            @SerializedName("num_cryptocurrencies") var numCryptocurrencies: Int,
            @SerializedName("error") var error: Any
    )
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