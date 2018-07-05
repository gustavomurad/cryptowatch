package io.pncc.cryptowatch.utilities

import android.content.Context
import io.pncc.cryptowatch.database.AppDatabase
import io.pncc.cryptowatch.database.HoldingsRepository
import io.pncc.cryptowatch.viewmodels.HoldingsListViewModelFactory

object InjectorUtils{

    private fun getHoldingsRepository(context: Context): HoldingsRepository{
        return HoldingsRepository.getInstance(AppDatabase.getInstance(context).holdingsDao())
    }

    fun provideHoldingsListViewModelFactory(context: Context): HoldingsListViewModelFactory{
        val repository = getHoldingsRepository(context)
        return HoldingsListViewModelFactory(repository)
    }
}