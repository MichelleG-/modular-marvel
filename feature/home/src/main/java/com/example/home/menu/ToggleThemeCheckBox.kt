package com.example.home.menu

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatCheckBox
import com.example.home.R

/**
 * Animated button menu item check box to apply night/light mode.
 *
 * @see AppCompatCheckBox
 */
class ToggleThemeCheckBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatCheckBox(context, attrs) {

    init {
        setButtonDrawable(R.drawable.asl_theme)
    }
}
