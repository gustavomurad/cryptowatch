package io.pncc.cryptowatch.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.pncc.cryptowatch.database.MarketRepository


class MarketListViewModelFactory(private val repository: MarketRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MarketListViewModel(repository) as T
}