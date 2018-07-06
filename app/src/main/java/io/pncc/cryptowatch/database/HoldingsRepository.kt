package io.pncc.cryptowatch.database

import io.pncc.cryptowatch.dao.HoldingsDao


class HoldingsRepository private constructor(
        private val holdingsDao: HoldingsDao
) {
    fun getHoldings() = holdingsDao.getHoldings()

    companion object {
        @Volatile private var instance: HoldingsRepository? = null

        fun getInstance(holdingsDao: HoldingsDao) =
                instance ?: synchronized(this) {
                    instance ?: HoldingsRepository(holdingsDao).also { instance = it }
                }
    }

}