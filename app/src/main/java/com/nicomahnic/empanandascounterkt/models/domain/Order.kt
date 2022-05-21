package com.nicomahnic.empanandascounterkt.models.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Order(
    val user: User,
    val date: Date,
    val comment: String,
    var selected: Boolean,
    var empanadaList: MutableList<Empanada> = mutableListOf(),
    val id: Int = 0,
) : Parcelable