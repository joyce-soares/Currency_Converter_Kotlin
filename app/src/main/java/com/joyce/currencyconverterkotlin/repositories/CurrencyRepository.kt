package com.joyce.currencyconverterkotlin.repositories

import com.joyce.currencyconverterkotlin.rest.RetrofitService
import okhttp3.ResponseBody
import retrofit2.Call

class CurrencyRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllCurrencies(): Call<Map<String, String>> = retrofitService.getAllCurrencies()
}