package com.example.empanandascounterkt.models.domain

data class Empanada (
    val name: String,
    var count: Int,
    val orderId: Int = 0
)