package com.example.empanandascounterkt.models.domain

data class User (
    val name: String,
    val empanadaList: List<Empanada>? = null,
)