package ru.practicum.android.diploma.features.favourites.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.features.favourites.domain.api.FavoriteVacanciesInteractor
import ru.practicum.android.diploma.features.favourites.domain.impl.FavoriteVacanciesInteractorImpl
import ru.practicum.android.diploma.features.favourites.presentation.FavoritesViewModel

val favouritesModule = module {

    single<FavoriteVacanciesInteractor> {
        FavoriteVacanciesInteractorImpl(get())
    }

    viewModel {
        FavoritesViewModel(get())
    }
}