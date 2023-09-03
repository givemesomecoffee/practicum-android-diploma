package ru.practicum.android.diploma.features.filter.ui.screen.workplace

import ru.practicum.android.diploma.features.filter.ui.model.AreaResult

data class WorkLocationUiState(
    val country: AreaResult?,
    val region: AreaResult?,
    val hasChanges: Boolean
)