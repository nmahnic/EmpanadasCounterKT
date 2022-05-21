package com.nicomahnic.empanandascounterkt.models.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Empanada (
    val name: String,
    var quantity: Int,
    val orderId: Int = 0
) : Parcelable