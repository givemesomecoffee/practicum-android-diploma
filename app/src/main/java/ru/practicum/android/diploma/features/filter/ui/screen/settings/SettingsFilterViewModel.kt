package ru.practicum.android.diploma.features.filter.ui.screen.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.features.filter.domain.impl.GetCachedFilterStateUseCaseImpl
import ru.practicum.android.diploma.features.filter.domain.impl.SaveNewFilterUseCaseImpl
import ru.practicum.android.diploma.features.filter.domain.model.Filter
import ru.practicum.android.diploma.features.filter.ui.SettingsFilterEvent
import ru.practicum.android.diploma.features.filter.ui.model.toDomain

class SettingsFilterViewModel(
    private val getCachedFilterStateUseCase: GetCachedFilterStateUseCaseImpl,
    private val saveNewFilterUseCase: SaveNewFilterUseCaseImpl
) : ViewModel() {

    private val _state = MutableLiveData(Pair(getCachedFilterStateUseCase.invoke(), false))
    val state: LiveData<Pair<Filter, Boolean>> = _state

    fun onEvent(event: SettingsFilterEvent) {
        when (event) {
            is SettingsFilterEvent.SalaryFilter -> {
                if (event.sum != _state.value?.first?.salary) {
                    _state.applyChanges(_state.value?.first?.copy(salary = event.sum))
                }
            }

            is SettingsFilterEvent.IncludeNoSalary -> {
                if (event.include != _state.value?.first?.showNoSalaryItems) {
                    _state.applyChanges(_state.value?.first?.copy(showNoSalaryItems = event.include))
                }
            }

            is SettingsFilterEvent.WorkPlaceFilter -> {
                _state.applyChanges(
                    _state.value?.first?.copy(
                        location = Filter.WorkLocation(
                            event.country?.toDomain(),
                            event.region?.toDomain()
                        )
                    )
                )
            }

            SettingsFilterEvent.ApplyChanges -> {
                saveFilter()
            }

            SettingsFilterEvent.ResetFilter -> {
                _state.applyChanges(getCachedFilterStateUseCase.invoke(), false)
            }

            SettingsFilterEvent.ResetWorkplace -> {
                _state.applyChanges(_state.value?.first?.copy(location = null))
            }
        }
    }

    private fun saveFilter() {
        viewModelScope.launch {
            _state.value?.let { saveNewFilterUseCase.invoke(it.first) }
        }
    }

    private fun MutableLiveData<Pair<Filter, Boolean>>.applyChanges(
        filter: Filter?,
        hasChanges: Boolean = true
    ) {
        filter?.let {
            this.value = this.value?.copy(it, hasChanges)
        }
    }
}