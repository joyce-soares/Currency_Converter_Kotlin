package com.joyce.currencyconverterkotlin.repositories

import com.joyce.currencyconverterkotlin.model.ApiResponseRate
import com.joyce.currencyconverterkotlin.rest.RetrofitServiceRate
import retrofit2.Call
import retrofit2.Response

class RateRepository constructor(private var retrofitServiceRate: RetrofitServiceRate) {

    fun getRateForAmount(from: String, to: String, amount: String): Call<ApiResponseRate?> =
        retrofitServiceRate.getRateForAmount(from = from, to = to, amount = amount)
}