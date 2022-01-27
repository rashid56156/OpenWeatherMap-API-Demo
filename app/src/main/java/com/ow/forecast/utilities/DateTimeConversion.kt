package com.ow.forecast.utilities

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateTimeConversion {
    private const val TIME_FORMAT = "hh:mm aaa"
    private const val DATE_TIME_FORMAT = "EEE dd MMM yyyy hh:mm aaa"

    fun changeDateFormat(inputDate: String): String{
        //2022-01-26 21:00:00
        var outputDate = inputDate
        val input = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH)
        val output = SimpleDateFormat("EEE, dd MMMM yyyy hh:mm aaa", Locale.ENGLISH)
        try {
            val getAbbreviate = input.parse(inputDate)    // parse input
            outputDate = output.format(getAbbreviate!!)    // format output
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return outputDate
    }


}