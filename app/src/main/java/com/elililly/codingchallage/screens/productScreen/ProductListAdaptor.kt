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
import com.elililly.codingchallage.models.Product


internal class ProductListAdaptor(private var productList: List<Product>) :
    RecyclerView.Adapter<ProductListAdaptor.MyViewHolder>(), ProductSummaryListner {
    val productListService: ProductListService = ProductListService()

    var orderedProduts = mutableListOf<Product>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.code
        val resId: Int = holder.itemView.context.resources.getIdentifier(
            product.url,
            "drawable",
            holder.itemView.context.getPackageName()
        )
        val image: Drawable = holder.itemView.context.resources.getDrawable(resId, null)
        holder.productImage.setImageDrawable(image)
        holder.productPrice.text = product.price.value.toString()
        holder.quantity.text = "0"

        holder.plusProduct.setOnClickListener(View.OnClickListener {
            val addProduct = productListService.addProduct(product)
            holder.quantity.text = addProduct.get(product).toString()
            Log.d("Inder", "Add product : " + addProduct)

        })
        holder.minusproduct.setOnClickListener(View.OnClickListener {
            val minusProduct = productListService.minusProduct(product)
            Log.d("Inder", "Minus product : " + minusProduct)
            holder.quantity.text = minusProduct.get(product).toString()
        })
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productImage: ImageView = view.findViewById(R.id.productImage)
        var productName: TextView = view.findViewById(R.id.productName)
        var productPrice: TextView = view.findViewById(R.id.productPrice)
        var plusProduct: Button = view.findViewById(R.id.plus)
        var quantity: TextView = view.findViewById(R.id.quantity)
        var minusproduct: Button = view.findViewById(R.id.minus)

    }

    override fun getToBeOrderedProducts(): Map<Product, Int> {
        return productListService.getProductList()
    }

}