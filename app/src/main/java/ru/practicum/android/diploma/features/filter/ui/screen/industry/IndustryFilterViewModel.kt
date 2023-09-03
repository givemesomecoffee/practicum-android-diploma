package ru.practicum.android.diploma.features.filter.ui.screen.industry

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.ui.lce.ContentState
import ru.practicum.android.diploma.core.ui.lce.asState
import ru.practicum.android.diploma.features.filter.domain.GetIndustriesUseCase
import ru.practicum.android.diploma.features.filter.ui.model.IndustryResult
import ru.practicum.android.diploma.features.filter.ui.screen.industry.model.toIndustryUi

class IndustryFilterViewModel(
    private val getIndustriesUseCase: GetIndustriesUseCase
) : ViewModel() {
    private val _state = MutableLiveData<ContentState<IndustryFilterUiState>>()
    val state: LiveData<ContentState<IndustryFilterUiState>> = _state

    private val industries = MutableStateFlow<List<IndustryUi>>(emptyList())

    fun sync(selectedIndustry: IndustryResult?) {
        viewModelScope.launch {
            _state.value = ContentState(isLoading = true)
            _state.value = runCatching {
                val industryList = getIndustriesUseCase.invoke().map { it.toIndustryUi(it.id == selectedIndustry?.id) }
                industries.value = industryList
                IndustryFilterUiState(industryList, selectedIndustry = selectedIndustry)
            }.asState()
        }
    }

    fun toggleSelection(state: IndustryUi) {
        val isChecked = !state.isChecked
        val prevContent = _state.value?.content
        industries.value = industries.value.map {
            it.copy(isChecked = isChecked && it.industry.id == state.industry.id)
        }
        _state.value = _state.value?.copy(
            content = prevContent?.copy(
                industryList = industries.value,
                hasChanges = true,
                selectedIndustry = state.industry.takeIf { isChecked }
            )
        )
    }

    fun filter(text: String) {
        val prevContent = _state.value?.content
        _state.value = _state.value?.copy(
            content = prevContent?.copy(
                industryList = if (text.isEmpty()) {
                    industries.value
                } else {
                    industries.value.filter { it.industry.name.contains(text) }
                }
            )
        )
    }
}