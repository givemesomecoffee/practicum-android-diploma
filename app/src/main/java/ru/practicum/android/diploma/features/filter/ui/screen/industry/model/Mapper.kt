package ru.practicum.android.diploma.features.filter.ui.screen.industry.model

import ru.practicum.android.diploma.features.filter.domain.model.Industry
import ru.practicum.android.diploma.features.filter.ui.model.toIndustryResult
import ru.practicum.android.diploma.features.filter.ui.screen.industry.IndustryUi

fun Industry.toIndustryUi(isSelected: Boolean) = IndustryUi(toIndustryResult(), isSelected)
