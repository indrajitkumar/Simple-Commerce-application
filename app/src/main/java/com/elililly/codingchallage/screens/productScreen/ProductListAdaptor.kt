package com.elililly.codingchallage.screens.productScreen

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elililly.codingchallage.R
import com.elililly.codingchallage.getImageFromDrawable
import com.elililly.codingchallage.models.Product


internal class ProductListAdaptor(private var productList: List<Product>) :
    RecyclerView.Adapter<ProductListAdaptor.MyViewHolder>(), ProductSummaryListner {
    private val productListService: ProductListService = ProductListService()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.code

        val image: Drawable = getImageFromDrawable(holder.itemView.context!!, product.url)

        holder.productImage.setImageDrawable(image)
        holder.productPrice.text = product.price.value.toString()
        holder.quantity.text = "0"

        holder.plusProduct.setOnClickListener {
            val addProduct = productListService.addProduct(product)
            holder.quantity.text = addProduct[product].toString()

        }
        holder.minusproduct.setOnClickListener {
            val minusProduct = productListService.minusProduct(product)
            if (minusProduct?.get(product) == null) holder.quantity.text = "0"
            else holder.quantity.text = minusProduct[product]?.toString()
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productImage: ImageView = view.findViewById(R.id.productImage)
        var productName: TextView = view.findViewById(R.id.productName)
        var productPrice: TextView = view.findViewById(R.id.productPrice)
        var plusProduct: ImageView = view.findViewById(R.id.plus)
        var quantity: TextView = view.findViewById(R.id.quantity)
        var minusproduct: ImageView = view.findViewById(R.id.minus)

    }

    override fun getToBeOrderedProducts(): Map<Product, Int> {
        val productList = productListService.getProductList()
        productList.values.removeAll{it == 0}
        return productList
    }

}