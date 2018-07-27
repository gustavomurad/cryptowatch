package io.pncc.cryptowatch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.pncc.cryptowatch.database.MarketRepository


class MarketListViewModelFactory(private val repository: MarketRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = MarketListViewModel(repository) as T
}