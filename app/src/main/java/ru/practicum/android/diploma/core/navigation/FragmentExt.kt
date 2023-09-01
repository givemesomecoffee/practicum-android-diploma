package ru.practicum.android.diploma.core.navigation

import androidx.fragment.app.Fragment

fun Fragment.hideBottomNavigation(hide: Boolean = true){
    (requireActivity() as? NavigationUiController)?.hideBottomNavigation(hide)
}