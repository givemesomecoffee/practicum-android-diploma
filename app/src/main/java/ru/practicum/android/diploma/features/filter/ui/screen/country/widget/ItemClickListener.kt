package ru.practicum.android.diploma.features.filter.ui.screen.country.widget

import ru.practicum.android.diploma.features.filter.domain.model.Area

interface ItemClickListener {
    fun onItemClick(area: Area)
}