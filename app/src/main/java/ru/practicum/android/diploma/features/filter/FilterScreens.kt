package ru.practicum.android.diploma.features.filter

import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.navigation.ActionScreen

object FilterScreens {

    object FilterSettings {
        fun getScreen(): ActionScreen = ActionScreen(
            R.id.action_fragment_to_filter_nav_graph,
            null
        )
    }

    object Workplace {
        fun getScreen(): ActionScreen = ActionScreen(
            R.id.action_settingsFilterFragment2_to_workPlaceFragment2,
            null
        )
    }
}