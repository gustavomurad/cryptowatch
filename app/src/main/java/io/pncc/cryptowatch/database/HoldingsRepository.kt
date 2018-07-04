package io.pncc.cryptowatch.database

import android.os.AsyncTask
import android.arch.lifecycle.LiveData
import android.app.Application
import io.pncc.cryptowatch.dao.HoldingsDao
import io.pncc.cryptowatch.model.Holdings


class HoldingsRepository {

    private val mHoldingsDao: HoldingsDao
    internal val allHoldings: LiveData<List<Holdings>>

    constructor(application: Application){
        val db: CryptocurrencyRoomDatabase = CryptocurrencyRoomDatabase.getInstance(application)
        mHoldingsDao = db.holdingsDao()
        allHoldings = mHoldingsDao.getHoldings()
    }

    fun insert(holdings: Holdings) {
        InsertAsyncTask(mHoldingsDao).execute(holdings)
    }

    private class InsertAsyncTask (private val mAsyncTaskDao: HoldingsDao) : AsyncTask<Holdings, Void, Void>() {

        override fun doInBackground(vararg params: Holdings): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}