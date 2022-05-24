package com.nicomahnic.empanandascounterkt.models.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User (
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val date: Date = Date()
) : Parcelable