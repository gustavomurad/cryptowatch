package io.pncc.cryptowatch.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.pncc.cryptowatch.database.Market
import io.pncc.cryptowatch.database.MarketRepository

class MarketListViewModel internal constructor(var marketRepository: MarketRepository): ViewModel() {
    private val markets = MediatorLiveData<Market>()

    init {
        refreshMarketData()
    }

    fun getMarketData() = markets

    fun refreshMarketData(){
        val liveMarket = marketRepository.getMarketData()
        markets.addSource(liveMarket, markets::setValue)
    }
}