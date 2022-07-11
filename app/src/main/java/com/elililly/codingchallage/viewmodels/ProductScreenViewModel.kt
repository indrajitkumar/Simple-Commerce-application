package com.elililly.codingchallage.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.elililly.codingchallage.utils.fromJson
import com.elililly.codingchallage.utils.getJsonDataFromAsset
import com.elililly.codingchallage.models.Products
import com.elililly.codingchallage.models.Stores

open class ProductScreenViewModel(internal val application: Application) :
    AndroidViewModel(application) {

    var mProducts: MutableLiveData<Products> = MutableLiveData<Products>()
    var mStores: MutableLiveData<Stores> = MutableLiveData<Stores>()

    private fun setProducts(products: Products) {
        mProducts.postValue(products)
    }

    private fun setStores(stores: Stores) {
        mStores.postValue(stores)
    }

    fun getProductResponse() {
        val jsonFileString = getJsonDataFromAsset(application.applicationContext, "Products.json")
        Log.i("data", jsonFileString!!)
        setProducts(jsonFileString.fromJson())
    }

    fun getStoreResponse() {
        val jsonDataFromAsset = getJsonDataFromAsset(application.applicationContext, "Stores.json")
        setStores(jsonDataFromAsset!!.fromJson())
    }

    init {
        getProductResponse()
        getStoreResponse()
    }
}