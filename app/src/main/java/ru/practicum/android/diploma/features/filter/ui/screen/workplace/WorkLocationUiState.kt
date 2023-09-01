package ru.practicum.android.diploma.features.filter.ui.screen.workplace

import ru.practicum.android.diploma.features.filter.domain.model.Area

data class WorkLocationUiState(
    val country: Area?,
    val region: Area?,
    val hasChanges: Boolean
)