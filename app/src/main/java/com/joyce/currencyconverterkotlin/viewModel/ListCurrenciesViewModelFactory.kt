package com.joyce.currencyconverterkotlin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joyce.currencyconverterkotlin.repositories.CurrencyRepository

class ListCurrenciesViewModelFactory constructor(private val repository: CurrencyRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListCurrenciesViewModel::class.java)) {
            ListCurrenciesViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}