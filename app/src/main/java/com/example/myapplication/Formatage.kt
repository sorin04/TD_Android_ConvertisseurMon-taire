package com.example.myapplication
import android.icu.text.DecimalFormat

object Formatage {
    fun formatage(number: String): String {
        val df = DecimalFormat("#.##")
        var result:String = df.format(number.toDouble())
        return result
    }
}