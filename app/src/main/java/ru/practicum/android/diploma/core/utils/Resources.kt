package ru.practicum.android.diploma.core.utils

import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat

@ColorInt
fun Resources.getColorCompat(@ColorRes colorRes: Int): Int {
    return ResourcesCompat.getColor(this, colorRes, null)
}