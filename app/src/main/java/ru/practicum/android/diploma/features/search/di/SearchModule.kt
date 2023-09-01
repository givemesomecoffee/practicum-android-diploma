package ru.practicum.android.diploma.features.search.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.features.search.presentation.SearchViewModel

val searchModule = module {
    viewModel {
        SearchViewModel()
    }
}