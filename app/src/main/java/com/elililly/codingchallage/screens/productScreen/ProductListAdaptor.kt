package com.elililly.codingchallage.screens.productScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elililly.codingchallage.databinding.ProductListItemBinding
import com.elililly.codingchallage.models.Product


internal class ProductListAdaptor(private var productList: List<Product>) :
    RecyclerView.Adapter<ProductListAdaptor.MyViewHolder>(), ProductSummaryListner {
    private val productListService: ProductListService = ProductListService()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ProductListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    internal inner class MyViewHolder(private val binding: ProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product

            binding.plus.setOnClickListener {
                val addProduct = productListService.addProduct(product)
                binding.quantity.text = addProduct[product].toString()
            }
            binding.minus.setOnClickListener {
                val minusProduct = productListService.minusProduct(product)
                if (minusProduct?.get(product) == null) binding.quantity.text = "0"
                else binding.quantity.text = minusProduct[product]?.toString()
            }
        }
    }

    override fun getToBeOrderedProducts(): Map<Product, Int> {
        val productList = productListService.getProductList()
        productList.values.removeAll { it == 0 }
        return productList
    }

}