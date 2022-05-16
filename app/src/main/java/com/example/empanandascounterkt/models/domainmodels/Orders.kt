package com.example.empanandascounterkt.models.domainmodels

class Orders(
    val empanadaList: MutableList<Empanada> = mutableListOf(),
    val user: User? = null,
)