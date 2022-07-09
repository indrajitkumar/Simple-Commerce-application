package com.elililly.codingchallage.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    var address: String? = null,
    var name: String? = null,
    var phone: String? = null
): Parcelable