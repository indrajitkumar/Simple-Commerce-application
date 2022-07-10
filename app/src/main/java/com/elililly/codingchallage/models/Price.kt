package com.elililly.codingchallage.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    val currencyIso: String,
    val discount: String,
    val value: String
):Parcelable