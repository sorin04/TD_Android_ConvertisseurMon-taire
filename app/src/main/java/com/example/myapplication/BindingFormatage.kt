package com.example.myapplication

import android.icu.text.DecimalFormat
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingFormatage {
    @BindingAdapter("formatage")
    @JvmStatic
    fun setFormatage(textView: TextView, value: String) {
        val df = DecimalFormat("#.##")
        textView.text = df.format(value.toDouble())
    }
}