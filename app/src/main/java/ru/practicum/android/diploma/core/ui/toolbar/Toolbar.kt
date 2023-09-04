package ru.practicum.android.diploma.core.ui.toolbar

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import ru.practicum.android.diploma.R

fun Fragment.enablePopUp(toolbar: MaterialToolbar){
    toolbar.setNavigationIcon(R.drawable.arrow_back)
    toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
}