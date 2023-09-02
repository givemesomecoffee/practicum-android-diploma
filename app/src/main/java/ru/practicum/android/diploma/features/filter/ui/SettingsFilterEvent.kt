package ru.practicum.android.diploma.features.filter.ui

import ru.practicum.android.diploma.features.filter.ui.model.AreaResult

sealed class SettingsFilterEvent {
    class IncludeNoSalary(val include: Boolean) : SettingsFilterEvent()
    class SalaryFilter(val sum: Long?) : SettingsFilterEvent()
    class WorkPlaceFilter(val country: AreaResult?, val region: AreaResult?) : SettingsFilterEvent()
    object ApplyChanges : SettingsFilterEvent()
    object ResetFilter : SettingsFilterEvent()
    object ResetWorkplace : SettingsFilterEvent()
}