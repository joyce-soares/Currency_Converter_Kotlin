package com.joyce.currencyconverterkotlin.model

data class Rate(
    val currency_name: String,
    val rate: String,
    val rate_for_amount: Double
)
