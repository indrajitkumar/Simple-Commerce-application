package com.elililly.codingchallage.screens.productScreen

import com.elililly.codingchallage.models.Product

class ProductListService {

    var hashMap: HashMap<Product, Int> = HashMap()
    fun getProductList() = hashMap

    fun addProduct(product: Product): HashMap<Product, Int> {
        if (hashMap.contains(product)) {
            hashMap[product] = hashMap[product]!!.plus(1)
        } else {
            hashMap[product] = 1
        }
        return hashMap
    }

    fun minusProduct(product: Product): HashMap<Product, Int>? {
        if (hashMap.contains(product)) {
            if (hashMap[product] == 0) return null
            hashMap[product] = hashMap[product]!!.minus(1)
        }
        return hashMap
    }
}