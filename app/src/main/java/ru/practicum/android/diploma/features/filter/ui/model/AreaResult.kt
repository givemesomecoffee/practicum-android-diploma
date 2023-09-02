package ru.practicum.android.diploma.features.filter.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AreaResult(
    val id: String,
    val name: String
) : Parcelable