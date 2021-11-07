package com.joyce.currencyconverterkotlin.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joyce.currencyconverterkotlin.model.Currency
import com.joyce.currencyconverterkotlin.repositories.CurrencyRepository
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListCurrenciesViewModel constructor(private val currencyRepository: CurrencyRepository): ViewModel() {

    val currenciesList = MutableLiveData<List<Currency>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCurrencies(){

        val response = currencyRepository.getAllCurrencies()
        response.enqueue(object : Callback<Map<String, String>> {

            override fun onResponse(call: Call<Map<String, String>>, response: Response<Map<String, String>>) {
                val body = response.body()
                val currencies = mutableListOf<Currency>()
                body?.forEach {
                    currencies.add(Currency(it.key, it.value))
                }
                currenciesList.postValue(currencies)
            }

            override fun onFailure(call: Call<Map<String, String>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}