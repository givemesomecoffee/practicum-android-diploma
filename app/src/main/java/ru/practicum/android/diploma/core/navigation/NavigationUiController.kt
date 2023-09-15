package ru.practicum.android.diploma.core.navigation

// TODO: remove
@Deprecated(message = ("bad design use navigation arguments instead"))
/**
 * @see [DefaultArguments.FULL_SCREEN]
 * @see filter_nav_graph
 */
interface NavigationUiController {
    fun hideBottomNavigation(hide: Boolean)
}