package com.example.empanandascounterkt.models.domain

import java.util.*

class Order(
    val user: User,
    val date: Date,
    val comment: String,
    val empanadaList: MutableList<Empanada> = mutableListOf(),
)