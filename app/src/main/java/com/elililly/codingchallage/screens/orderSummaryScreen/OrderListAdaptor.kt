package com.elililly.codingchallage.screens.orderSummaryScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elililly.codingchallage.databinding.OderedProductListItemBinding
import com.elililly.codingchallage.models.Product

class OrderListAdaptor(private var orderList: Map<Product, Int>) :
    RecyclerView.Adapter<OrderListAdaptor.MyOrderViewHolder>() {
    private var list = mutableListOf<Product>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyOrderViewHolder {
        return MyOrderViewHolder(OderedProductListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyOrderViewHolder, position: Int) {
        list = ArrayList(orderList.keys)
        val product = list[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class MyOrderViewHolder(private var binding: OderedProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product

        }
    }
}