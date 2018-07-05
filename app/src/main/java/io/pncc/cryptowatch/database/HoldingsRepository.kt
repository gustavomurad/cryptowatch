package io.pncc.cryptowatch.database

import io.pncc.cryptowatch.dao.HoldingsDao
import io.pncc.cryptowatch.model.Holdings


class HoldingsRepository private constructor(private val holdingsDao: HoldingsDao){

    fun getHoldings() = holdingsDao.getHoldings()

    fun insert(holdings: Holdings){
        holdingsDao.insert(holdings)
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: HoldingsRepository? = null

        fun getInstance(holdingsDao: HoldingsDao) =
                instance ?: synchronized(this) {
                    instance ?: HoldingsRepository(holdingsDao).also { instance = it }
                }
    }

}