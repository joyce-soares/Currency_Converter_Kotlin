package com.joyce.currencyconverterkotlin.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joyce.currencyconverterkotlin.databinding.ActivityMainBinding
import com.joyce.currencyconverterkotlin.repositories.RateRepository
import com.joyce.currencyconverterkotlin.rest.RetrofitServiceRate
import com.joyce.currencyconverterkotlin.viewModel.ConvertViewModel
import com.joyce.currencyconverterkotlin.viewModel.ConvertViewModelFactory
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var activityLauncher: ActivityResultLauncher<Intent>
    private lateinit var activityLauncher2: ActivityResultLauncher<Intent>
    private lateinit var from: String
    private lateinit var to: String
    private lateinit var amount: String

    private lateinit var viewModel: ConvertViewModel
    private val retrofitServiceRate = RetrofitServiceRate.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, ConvertViewModelFactory(RateRepository(retrofitServiceRate)))
            .get(ConvertViewModel::class.java)

        activityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->

            if (result.resultCode == 1) {
                intent = result.data

                if (intent != null) {
                    var data = intent.getStringExtra("result")
                    from = data.toString()
                    binding.btnCurrencyFrom.setText(from)
                }
            }
        }

        activityLauncher2 = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == 1) {
                intent = result.data

                if (intent != null) {
                    var data = intent.getStringExtra("result")
                    to = data.toString()
                    binding.btnCurrencyTo.setText(to)
                }
            }
        }

        binding.btnCurrencyFrom.setOnClickListener {
            intent = Intent(this, ListCurrenciesActivity::class.java)
            activityLauncher.launch(intent)
        }

        binding.btnCurrencyTo.setOnClickListener {
            intent = Intent(this, ListCurrenciesActivity::class.java)
            activityLauncher2.launch(intent)
        }

        binding.btnConvert.setOnClickListener {

            amount = binding.editAmount.text.toString()
            viewModel.getRateAmount(from, to, amount)
            observe()
        }
    }

    private fun observe() {

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.total.observe(this) {

            var dec = DecimalFormat("#,###.00")
            binding.textResult.text = dec.format(it.rate_for_amount)
        }
    }
}