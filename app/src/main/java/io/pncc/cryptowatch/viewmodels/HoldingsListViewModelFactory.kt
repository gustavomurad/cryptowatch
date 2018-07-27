package io.pncc.cryptowatch.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.pncc.cryptowatch.database.HoldingsRepository

class HoldingsListViewModelFactory(
        private val repository: HoldingsRepository
) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = HoldingsListViewModel(repository) as T
}