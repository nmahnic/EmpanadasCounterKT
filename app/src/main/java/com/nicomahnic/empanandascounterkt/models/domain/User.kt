package com.nicomahnic.empanandascounterkt.models.domain

import java.util.*

data class User (
    val name: String,
    val date: Date = Date()
)