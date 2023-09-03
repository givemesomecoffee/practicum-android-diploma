package ru.practicum.android.diploma.core.ui.lce

data class ContentState <T>(
    val isLoading: Boolean,
    val content: T? = null,
    val error: Throwable? = null
)
