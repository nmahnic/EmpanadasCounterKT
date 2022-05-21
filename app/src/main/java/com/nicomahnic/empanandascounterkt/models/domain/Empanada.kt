package com.nicomahnic.empanandascounterkt.models.domain

data class Empanada (
    val name: String,
    var quantity: Int,
    val orderId: Int = 0
)