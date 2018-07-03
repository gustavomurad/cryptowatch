package io.pncc.cryptowatch.model

data class Holdings(
        var id: Int,
        var name: String,
        var symbol: String,
        var price: Double,
        var percentChange24h: Double)