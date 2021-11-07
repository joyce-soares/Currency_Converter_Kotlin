package com.joyce.currencyconverterkotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joyce.currencyconverterkotlin.databinding.ItemCurrencyBinding
import com.joyce.currencyconverterkotlin.model.Currency


class CurrencyAdapter(private val onItemClicked: (Currency) -> Unit): RecyclerView.Adapter<CurrencyAdapter.MyViewHolder>() {

    private var listCurrencies = mutableListOf<Currency>()

    @SuppressLint("NotifyDataSetChanged")
    fun setCurrencies(list: List<Currency>){
        this.listCurrencies = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currency = listCurrencies[position]
        holder.bind(currency, onItemClicked)
    }

    override fun getItemCount() = listCurrencies.size

    class MyViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(currency: Currency, onItemClicked: (Currency) -> Unit){
            binding.textNameCurrency.text = currency.name

            itemView.setOnClickListener{
                onItemClicked(currency)
            }
        }
    }
}