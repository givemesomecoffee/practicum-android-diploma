package ru.practicum.android.diploma.features.filter.ui.screen.region

import ru.practicum.android.diploma.features.filter.ui.screen.region.model.RegionUi

data class RegionFilterUiState(
    val regionList: List<RegionUi>,
    val hasChanges: Boolean = false,
    val selectedRegion: RegionUi? = null
)