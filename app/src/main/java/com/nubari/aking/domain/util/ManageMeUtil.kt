package com.nubari.aking.domain.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class ManageMeUtil {

    companion object Methods {
        fun getCurrentDateTime(): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDate.now()
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")
                current.format(formatter)
            } else {
                val date = Date()
                val formatter = SimpleDateFormat("MMM dd yyyy HH:mma", Locale.getDefault())
                formatter.format(date)
            }
        }
    }
}