package ru.practicum.android.diploma.features.filter

import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.navigation.ActionScreen

object FilterScreens {

    object FilterSettings {
        fun getScreen(): ActionScreen = ActionScreen(
            R.id.action_searchFragment_to_settingsFilterFragment,
            null
        )
    }
}