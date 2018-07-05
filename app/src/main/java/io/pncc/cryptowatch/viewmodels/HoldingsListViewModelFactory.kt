package io.pncc.cryptowatch.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.pncc.cryptowatch.database.HoldingsRepository

class HoldingsListViewModelFactory(
        private val repository: HoldingsRepository
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = HoldingsListViewModel(repository) as T
}