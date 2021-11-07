package com.joyce.currencyconverterkotlin.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.joyce.currencyconverterkotlin.repositories.RateRepository

class ConvertViewModelFactory(
    private val repository: RateRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ConvertViewModel::class.java)) {
            ConvertViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}