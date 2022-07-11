package com.elililly.codingchallage.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
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

fun getImageFromDrawable(context: Context, urlString: String): Drawable {
    val resId: Int = context.resources.getIdentifier(
        urlString,
        "drawable",
        context.packageName
    )
    return context.resources.getDrawable(resId, null)
}

inline fun <reified T : Any> T.json(): String = Gson().toJson(this, T::class.java)

inline fun <reified T : Any> String.fromJson(): T = Gson().fromJson(this,T::class.java)

//To generate random order Id
fun IntRange.random() = (Math.random() * ((endInclusive + 1) - start) + start).toInt()

fun View.hideSoftInput() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}