package com.joyce.currencyconverterkotlin.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joyce.currencyconverterkotlin.model.ApiResponseRate
import com.joyce.currencyconverterkotlin.model.Rate
import com.joyce.currencyconverterkotlin.repositories.RateRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConvertViewModel constructor(
    private var repository: RateRepository,
): ViewModel() {

    //val convertedRate = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val total = MutableLiveData<Rate>()

    fun getRateAmount(from: String, to: String,amount: String){

        val response = repository.getRateForAmount(from, to, amount)
        response.enqueue(object : Callback<ApiResponseRate?>{
            override fun onResponse(call: Call<ApiResponseRate?>, response: Response<ApiResponseRate?>){
                val body = response.body()

                body?.rates?.get(to)?.let {
                    total.postValue(it)
                }
            }

            override fun onFailure(call: Call<ApiResponseRate?>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}
