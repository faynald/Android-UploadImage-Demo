package com.farhanrv.uploadimagedemo.utils

import java.text.SimpleDateFormat
import java.util.*

object DateHelper {
    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }
    fun convertDateToLong(dates: String): Long {
        val date = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dates)
        return date.time
    }
    fun convertLongToDate(num: Long): String {
        val date = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        return date.format(num)
    }
}