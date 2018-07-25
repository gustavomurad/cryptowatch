package io.pncc.cryptowatch.utilities

import android.content.Context
import io.pncc.cryptowatch.database.AppDatabase
import io.pncc.cryptowatch.database.HoldingsRepository
import io.pncc.cryptowatch.database.MarketRepository
import io.pncc.cryptowatch.viewmodels.HoldingsListViewModelFactory
import io.pncc.cryptowatch.viewmodels.MarketListViewModelFactory

object InjectorUtils{

    private fun getHoldingsRepository(context: Context): HoldingsRepository{
        return HoldingsRepository.getInstance(AppDatabase.getInstance(context).holdingsDao())
    }

    fun provideHoldingsListViewModelFactory(context: Context): HoldingsListViewModelFactory{
        val repository = getHoldingsRepository(context)
        return HoldingsListViewModelFactory(repository)
    }

    private fun getMarketRepository(): MarketRepository{
        return MarketRepository.getInstance()
    }

    fun provideMarketViewModelFactory(): MarketListViewModelFactory{
        val repository = getMarketRepository()
        return MarketListViewModelFactory(repository)
    }

}