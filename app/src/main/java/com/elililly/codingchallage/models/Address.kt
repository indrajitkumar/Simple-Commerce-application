package com.elililly.codingchallage.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    var address: String,
    var name: String,
    var phone: String
): Parcelable