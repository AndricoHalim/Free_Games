package com.andricohalim.freegames.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun loadImage(context: Context, url: String, imageView: ImageView) {
    Glide.with(context)
        .load(url)
        .into(imageView)
}

fun formatDate(endDateStr: String): String {
    if (endDateStr.equals("N/A", ignoreCase = true)) {
        return "Not Available"
    }

    // Adjust the pattern based on the actual format of games.endDate
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM yyyy HH:mm:ss", Locale("US", "US"))

    return try {
        val endDate: Date = inputFormat.parse(endDateStr) ?: Date()
        outputFormat.format(endDate)
    } catch (e: ParseException) {
        e.printStackTrace()
        "Invalid Date Format"
    }
}