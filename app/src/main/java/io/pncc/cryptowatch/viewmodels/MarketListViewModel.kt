package io.pncc.cryptowatch.viewmodels

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
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