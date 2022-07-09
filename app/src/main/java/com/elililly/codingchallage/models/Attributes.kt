package com.elililly.codingchallage.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attributes(
    var address: Address,
    var orderId: String,
    val paymentMethod: String
):Parcelable