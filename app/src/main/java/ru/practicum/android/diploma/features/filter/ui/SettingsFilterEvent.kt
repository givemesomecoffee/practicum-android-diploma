package ru.practicum.android.diploma.features.filter.ui

sealed class SettingsFilterEvent {
    class IncludeNoSalary(val include: Boolean) : SettingsFilterEvent()
    class SalaryFilter(val sum: Long?) : SettingsFilterEvent()
    class WorkPlaceFilter(val country: String?, val region: String?) : SettingsFilterEvent()
    object ApplyChanges : SettingsFilterEvent()
    object ResetFilter : SettingsFilterEvent()

}