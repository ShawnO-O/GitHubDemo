package com.shawn.githubdemo.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun getTokenFromAssets(context: Context): String {
    return try {
        val inputStream = context.assets.open("TokenKey.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String? = reader.readLine()
        while (line != null) {
            stringBuilder.append(line)
            line = reader.readLine()
        }
        reader.close()
        stringBuilder.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}