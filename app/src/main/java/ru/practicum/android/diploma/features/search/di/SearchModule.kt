package ru.practicum.android.diploma.features.search.di

import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.practicum.android.diploma.features.search.data.network.SearchAPI
import ru.practicum.android.diploma.features.search.data.network.SearchRepositoryImpl
import ru.practicum.android.diploma.features.search.domain.VacanciesInteractor
import ru.practicum.android.diploma.features.search.domain.api.SearchRepository
import ru.practicum.android.diploma.features.search.domain.impl.VacanciesInteractorImpl
import ru.practicum.android.diploma.features.search.presentation.SearchViewModel

val searchModule = module {
    viewModel {
        SearchViewModel(get())
    }
    single<SearchRepository> {
        SearchRepositoryImpl(get(), androidContext())
    }
    single<VacanciesInteractor> {
        VacanciesInteractorImpl(get())
    }
    single {
        provideSearchApi(get())
    }

}

private fun provideSearchApi(retrofit: Retrofit) = retrofit.create(SearchAPI::class.java)
