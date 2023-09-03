package ru.practicum.android.diploma.features.filter.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Area(
    val id: String,
    val name: String,
    val parentId: String?
): Parcelable

