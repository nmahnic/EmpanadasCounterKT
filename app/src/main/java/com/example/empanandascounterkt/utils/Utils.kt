package com.example.empanandascounterkt.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun parseTimestamp(date: Date): String {
        val simpleDate = SimpleDateFormat("hh:mm dd/MM/yy")
        return simpleDate.format(date)
    }
}