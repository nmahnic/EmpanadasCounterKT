package com.example.empanandascounterkt.models.domain

import java.util.*

class Order(
    val user: User,
    val date: Date,
    val comment: String,
    var empanadaList: MutableList<Empanada> = mutableListOf(),
    val id: Int = 0,
)