package com.elililly.codingchallage

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.gson.Gson
import java.io.IOException

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

inline fun <reified T : Any> T.json(): String = Gson().toJson(this, T::class.java)

inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this,T::class.java)

fun IntRange.random() = (Math.random() * ((endInclusive + 1) - start) + start).toInt()
