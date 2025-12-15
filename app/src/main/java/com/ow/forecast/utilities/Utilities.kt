package com.ow.forecast.utilities

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

object Utilities {

    fun formatDate(input: String?): String {
        if(input == null) return ""
        val inputFormatter = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd HH:mm:ss",
            Locale.ENGLISH
        )

        val dateTime = LocalDateTime.parse(input, inputFormatter)

        val timeFormatter = DateTimeFormatter.ofPattern(
            "hh:mm a",
            Locale.ENGLISH
        )

        val dateFormatter = DateTimeFormatter.ofPattern(
            "MMM dd, yyyy",
            Locale.ENGLISH
        )

        val timePart = dateTime.format(timeFormatter)
        val datePart = dateTime.format(dateFormatter)

        return "$timePart / $datePart"
    }
}