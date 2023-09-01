package ru.practicum.android.diploma.features.filter.ui

import ru.practicum.android.diploma.features.filter.domain.model.Area

sealed class SettingsFilterEvent {
    class IncludeNoSalary(val include: Boolean) : SettingsFilterEvent()
    class SalaryFilter(val sum: Long?) : SettingsFilterEvent()
    class WorkPlaceFilter(val country: Area?, val region: Area?) : SettingsFilterEvent()
    object ApplyChanges : SettingsFilterEvent()
    object ResetFilter : SettingsFilterEvent()
    object ResetWorkplace: SettingsFilterEvent()
}