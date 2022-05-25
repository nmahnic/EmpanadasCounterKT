package com.nicomahnic.empanandascounterkt.models.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Delivery(
    val id: Int = 0,
    val name: String,
    val address: String,
    val city: String,
    val postCode: String,
    val date: Long = Date().time,
    val whatsappNumber: String
): Parcelable
