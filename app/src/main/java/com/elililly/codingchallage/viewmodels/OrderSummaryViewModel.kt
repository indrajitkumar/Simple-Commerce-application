package com.elililly.codingchallage.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.elililly.codingchallage.fromJson
import com.elililly.codingchallage.getJsonDataFromAsset
import com.elililly.codingchallage.models.OrderSubmit
import com.elililly.codingchallage.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("app_preferences")

class OrderSummaryViewModel(internal val application: Application) : AndroidViewModel(application) {
    var mOrderSubmit: MutableLiveData<OrderSubmit> = MutableLiveData<OrderSubmit>()
    var mDataStoreManager: MutableLiveData<Flow<String?>> = MutableLiveData<Flow<String?>>()
    var totalPrice: MutableLiveData<String> = MutableLiveData<String>()

    companion object {
        val SUBMIT_ORDER = stringPreferencesKey("SUBMIT_ORDER")
    }

    private suspend fun storeSubmitOrder(saveSubmitOrder: String) {
        application.dataStore.edit { prefs ->
            prefs[SUBMIT_ORDER] = saveSubmitOrder
        }
    }

    private val getSubmitOrderResponse: Flow<String?>
        get() = application.dataStore.data.map {
            it[SUBMIT_ORDER] ?: ""
        }
    private fun setOrderSubmit(orderSubmit: OrderSubmit) {
        mOrderSubmit.postValue(orderSubmit)
    }

    private fun getOrderSubmitResponse() {
        val jsonFileString = getJsonDataFromAsset(application.applicationContext, "OrderDone.json")
        Log.i("data", jsonFileString!!)
        setOrderSubmit(jsonFileString.fromJson())
    }

    suspend fun storeLocally(storeData: String) {
        storeSubmitOrder(storeData)
        mDataStoreManager.postValue(getFromStorage())
    }

    private fun getFromStorage() = getSubmitOrderResponse

    fun totalPrice(productsToBeOrder: Map<Product, Int>){
        totalPrice.postValue(getTotalPrice(productsToBeOrder))
    }
    private fun getTotalPrice(productsToBeOrder: Map<Product, Int>):String {
        var total = 0.0
        for ((k,v) in productsToBeOrder){
            total += (k.price.value.toFloat() * v)
        }
        return total.toString()
    }

    init {
        getOrderSubmitResponse()
    }


}