package com.joyce.currencyconverterkotlin.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joyce.currencyconverterkotlin.adapter.CurrencyAdapter
import com.joyce.currencyconverterkotlin.databinding.ActivityListCurrenciesBinding
import com.joyce.currencyconverterkotlin.model.Currency
import com.joyce.currencyconverterkotlin.repositories.CurrencyRepository
import com.joyce.currencyconverterkotlin.rest.RetrofitService
import com.joyce.currencyconverterkotlin.viewModel.ListCurrenciesViewModel
import com.joyce.currencyconverterkotlin.viewModel.ListCurrenciesViewModelFactory

class ListCurrenciesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListCurrenciesBinding
    private lateinit var viewModel: ListCurrenciesViewModel
    private val retrofitService = RetrofitService.getInstance()

    private val adapter = CurrencyAdapter{
        click(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCurrenciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ListCurrenciesViewModelFactory(CurrencyRepository(retrofitService)))
            .get(ListCurrenciesViewModel::class.java)

        binding.recyclerCurrencies.layoutManager = LinearLayoutManager(this)
        binding.recyclerCurrencies.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.currenciesList.observe(this, Observer {
            adapter.setCurrencies(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllCurrencies()
    }

    private fun click(currency: Currency){

        var intent = Intent()
        intent.putExtra("result", currency.code)
        setResult(1, intent)

        finish()
    }
}