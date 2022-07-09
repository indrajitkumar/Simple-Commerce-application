package com.elililly.codingchallage.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    val currencyIso: String,
    val discount: Int,
    val value: Double
):Parcelable