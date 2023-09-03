package ru.practicum.android.diploma.features.filter.ui.screen.region.model

import ru.practicum.android.diploma.features.filter.domain.model.Area
import ru.practicum.android.diploma.features.filter.ui.model.AreaResult
import ru.practicum.android.diploma.features.filter.ui.model.toAreaResult

data class RegionUi(
    val region: AreaResult,
    val country: AreaResult,
    val isChecked: Boolean = false
) {
    companion object {
        fun mapFrom(region: Area, country: Area, selectedRegion: AreaResult?): RegionUi {
            return RegionUi(region.toAreaResult(), country.toAreaResult(), region.id == selectedRegion?.id)
        }
    }
}