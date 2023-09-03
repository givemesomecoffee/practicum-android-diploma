package ru.practicum.android.diploma.core.navigation

import androidx.fragment.app.Fragment

// TODO: remove
/**
 * @see [DefaultArguments.FULL_SCREEN]
 * @see nav_graph
 */
@Deprecated(message = ("bad design use navigation arguments instead"))
fun Fragment.hideBottomNavigation(hide: Boolean = true) {
    (requireActivity() as? NavigationUiController)?.hideBottomNavigation(hide)
}