package com.elililly.codingchallage

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.elililly.codingchallage.DataStoreManager.Companion.SUBMIT_ORDER
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val mContext: Context) {
    private val Context.dataStore by preferencesDataStore("app_preferences")

    companion object {
        val SUBMIT_ORDER = stringPreferencesKey("SUBMIT_ORDER")
    }

     suspend fun storeSubmitOrder(saveSubmitOrder: String) {
        mContext.dataStore.edit { prefs ->
            prefs[SUBMIT_ORDER] = saveSubmitOrder
        }
    }

    val getSubmitOrderResponse: Flow<String?>
        get() = mContext.dataStore.data.map {
            it[SUBMIT_ORDER] ?: ""
        }

}