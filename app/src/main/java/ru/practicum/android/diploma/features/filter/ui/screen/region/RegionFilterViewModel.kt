package ru.practicum.android.diploma.features.filter.ui.screen.region

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.ui.lce.ContentState
import ru.practicum.android.diploma.core.ui.lce.asState
import ru.practicum.android.diploma.features.filter.domain.GetAreasUseCase
import ru.practicum.android.diploma.features.filter.ui.model.AreaResult
import ru.practicum.android.diploma.features.filter.ui.screen.region.model.RegionUi

class RegionFilterViewModel(
    private val getAreasUseCase: GetAreasUseCase
) : ViewModel() {
    private val _state = MutableLiveData<ContentState<RegionFilterUiState>>()
    val state: LiveData<ContentState<RegionFilterUiState>> = _state

    private val regions = MutableStateFlow<List<RegionUi>>(emptyList())

    fun sync(countryId: String?, selectedRegion: AreaResult?) {
        viewModelScope.launch {
            _state.value = ContentState(isLoading = true)
            _state.value = runCatching {
                val regionList = getAreasUseCase.invoke(countryId).flatMap { country ->
                    country.areas?.map { RegionUi.mapFrom(it, country, selectedRegion) }
                        ?: emptyList()
                }
                regions.value = regionList
                RegionFilterUiState(regionList)
            }.asState()
        }
    }

    fun toggleSelection(state: RegionUi) {
        val isChecked = !state.isChecked
        val prevContent = _state.value?.content
        regions.value = regions.value.map {
            it.copy(isChecked = isChecked && it.region.id == state.region.id)
        }
        _state.value = _state.value?.copy(
            content = prevContent?.copy(
                regionList = regions.value,
                hasChanges = true,
                selectedRegion = state.takeIf { isChecked }
            )
        )
    }

    fun filter(text: String) {
        val prevContent = _state.value?.content
        _state.value = _state.value?.copy(
            content = prevContent?.copy(
                regionList = if (text.isEmpty()) {
                    regions.value
                } else {
                    regions.value.filter { it.region.name.contains(text) }
                }
            )
        )
    }
}