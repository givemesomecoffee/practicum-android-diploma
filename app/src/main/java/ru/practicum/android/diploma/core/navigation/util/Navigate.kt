package ru.practicum.android.diploma.core.navigation.util

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.practicum.android.diploma.core.navigation.ActionScreen

fun Fragment.goToScreen(screen: ActionScreen) {
    findNavController().navigate(screen.actionId, screen.bundle)
}