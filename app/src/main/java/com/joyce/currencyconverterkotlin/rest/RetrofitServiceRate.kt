package com.joyce.currencyconverterkotlin.rest

import com.joyce.currencyconverterkotlin.helper.Endpoints
import com.joyce.currencyconverterkotlin.model.ApiResponseRate
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitServiceRate {

    @GET(Endpoints.CONVERT)
    fun getRateForAmount(
        @Query("api_key") apiKey: String = Endpoints.API_KEY,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: String,
        ): Call<ApiResponseRate?>

    companion object {

        private val retrofitServiceRate: RetrofitServiceRate by lazy {

            val retrofit = Retrofit.Builder()
                .baseUrl(Endpoints.BASE_URL_RATE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitServiceRate::class.java)
        }

        fun getInstance(): RetrofitServiceRate {
            return retrofitServiceRate
        }
    }
}