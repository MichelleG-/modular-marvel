package com.example.datasource.di.util

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

interface ThemeUtils {

    fun isDarkTheme(context: Context): Boolean

    fun isLightTheme(context: Context): Boolean

    fun setNightMode(forceNight: Boolean, delay: Long = 0L)
}