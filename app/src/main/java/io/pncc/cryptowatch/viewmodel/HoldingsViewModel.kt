package io.pncc.cryptowatch.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import io.pncc.cryptowatch.database.HoldingsRepository
import io.pncc.cryptowatch.model.Holdings

class HoldingsViewModel: AndroidViewModel{
    private var mHoldingsRepository: HoldingsRepository
    private var mAllHoldings: LiveData<List<Holdings>>

    constructor(application: Application) : super(application){
        mHoldingsRepository = HoldingsRepository(application)
        mAllHoldings = mHoldingsRepository.allHoldings

    }

    fun getAllHoldings(): LiveData<List<Holdings>>{
        return this.mAllHoldings
    }

    fun insert(holdings: Holdings){
        this.mHoldingsRepository.insert(holdings)
    }
}