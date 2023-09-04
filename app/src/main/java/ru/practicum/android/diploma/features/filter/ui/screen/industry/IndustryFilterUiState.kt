package ru.practicum.android.diploma.features.filter.ui.screen.industry

import ru.practicum.android.diploma.features.filter.ui.model.IndustryResult

data class IndustryFilterUiState(
    val industryList: List<IndustryUi>,
    val hasChanges: Boolean = false,
    val selectedIndustry: IndustryResult? = null
)