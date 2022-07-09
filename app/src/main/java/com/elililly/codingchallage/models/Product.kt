package com.elililly.codingchallage.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val code: String,
    val price: Price,
    val stock: Stock,
    val url: String
):Parcelable