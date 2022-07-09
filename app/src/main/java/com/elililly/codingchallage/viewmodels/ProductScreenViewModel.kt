package com.elililly.codingchallage.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elililly.codingchallage.fromJson
import com.elililly.codingchallage.getJsonDataFromAsset
import com.elililly.codingchallage.models.Address
import com.elililly.codingchallage.models.Products
import com.elililly.codingchallage.models.Stores

class ProductScreenViewModel(internal val application: Application) :
    AndroidViewModel(application) {

    var mProducts: MutableLiveData<Products> = MutableLiveData<Products>()
    var mStores: MutableLiveData<Stores> = MutableLiveData<Stores>()

    private fun setProducts(products: Products) {
        mProducts.postValue(products)
    }

    private fun setStores(stores: Stores) {
        mStores.postValue(stores)
    }

    private fun getProductResponse() {
        val jsonFileString = getJsonDataFromAsset(application.applicationContext, "Products.json")
        Log.i("data", jsonFileString!!)
        setProducts(jsonFileString.fromJson())
    }

    private fun getStoreResponse() {
        val jsonDataFromAsset = getJsonDataFromAsset(application.applicationContext, "Stores.json")
        setStores(jsonDataFromAsset!!.fromJson())
    }

    init {
        getProductResponse()
        getStoreResponse()
    }
}