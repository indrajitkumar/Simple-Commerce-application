package com.elililly.codingchallage.screens.productScreen

import com.elililly.codingchallage.models.Product

interface ProductSummaryListner {
    fun getToBeOrderedProducts() :  Map<Product, Int>
}