package com.joyce.currencyconverterkotlin.model

data class ApiResponseRate (
    val base_currency_code: String,
    val base_currency_name: String,
    val amount: String,
    val updated_date: String,
    var rates: Map<String, Rate> = HashMap(),
    val status: String,
)