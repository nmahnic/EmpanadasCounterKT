package com.example.empanandascounterkt.models.domainmodels

data class User (
    val name: String,
    val comment: String,
    val empanadaList: List<Empanada>? = null
)