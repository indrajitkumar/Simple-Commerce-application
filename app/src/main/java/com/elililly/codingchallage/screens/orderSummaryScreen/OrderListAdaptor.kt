package com.elililly.codingchallage.screens.orderSummaryScreen

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elililly.codingchallage.R
import com.elililly.codingchallage.databinding.OderedProductListItemBinding
import com.elililly.codingchallage.databinding.ProductListItemBinding
import com.elililly.codingchallage.getImageFromDrawable
import com.elililly.codingchallage.models.Product

class OrderListAdaptor(private var orderList: Map<Product, Int>) :
    RecyclerView.Adapter<OrderListAdaptor.MyOrderViewHolder>() {
    var list = mutableListOf<Product>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyOrderViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.odered_product_list_item, parent, false)
        return MyOrderViewHolder(OderedProductListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyOrderViewHolder, position: Int) {
        list = ArrayList(orderList.keys)
        val product = list[position]
        holder.bind(product)
//        holder.productName.text = product.code
//        val image: Drawable = getImageFromDrawable(holder.itemView.context!!, product.url)
//
//        holder.productImage.setImageDrawable(image)
//        holder.productPrice.text = product.price.value.toString()
//        holder.quantity.text = orderList.getValue(product).toString()
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    inner class MyOrderViewHolder(private var binding: OderedProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //        var productImage: ImageView = view.findViewById(R.id.productImage)
//        var productName: TextView = view.findViewById(R.id.productName)
//        var quantity: TextView = view.findViewById(R.id.qunatity)
//        var discountPrice: TextView = view.findViewById(R.id.discountPrice)
//        var productPrice: TextView = view.findViewById(R.id.productPrice)
        fun bind(product: Product) {
            binding.product = product

        }
    }
}