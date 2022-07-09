package com.elililly.codingchallage.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    val attributes: Attributes,
    val type: String
):Parcelable