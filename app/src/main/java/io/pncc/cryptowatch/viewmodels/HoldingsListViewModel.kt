package io.pncc.cryptowatch.viewmodels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import io.pncc.cryptowatch.database.HoldingsRepository
import io.pncc.cryptowatch.model.Holdings

class HoldingsListViewModel internal constructor(
        private val holdingsRepository: HoldingsRepository
) : AndroidViewModel(){
    private val holdingsList = MediatorLiveData<List<Holdings>>()

    init {
        val liveHoldingsList = holdingsRepository.getHoldings()

        holdingsList.addSource(liveHoldingsList, holdingsList::setValue)
    }


    fun getHoldings() = holdingsList
}