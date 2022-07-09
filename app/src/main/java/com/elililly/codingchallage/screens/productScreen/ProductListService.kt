package com.elililly.codingchallage.screens.productScreen

import com.elililly.codingchallage.models.Product

class ProductListService {

    var hashMap: HashMap<Product, Int> = HashMap()
    fun getProductList() = hashMap

    fun addProduct(product: Product): HashMap<Product, Int> {
        if (hashMap.contains(product)) {
            hashMap.put(product, hashMap.get(product)!!.plus(1))
        } else {
            hashMap.put(product, 1)
        }
        return hashMap
    }

    fun minusProduct(product: Product): HashMap<Product, Int> {
        if (hashMap.contains(product)) {
            if (hashMap.get(product) == 0) return hashMap
            hashMap.put(product, hashMap.get(product)!!.minus(1))
        }
        return hashMap
    }
}