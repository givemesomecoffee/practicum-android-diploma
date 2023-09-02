package ru.practicum.android.diploma.features.filter.ui.screen.industry

import ru.practicum.android.diploma.features.filter.ui.model.IndustryResult

data class IndustryUi(
    val industry: IndustryResult,
    val isChecked: Boolean = false
)