package ru.practicum.android.diploma.features.similar.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.practicum.android.diploma.features.similar.data.network.SimilarVacanciesApi
import ru.practicum.android.diploma.features.similar.data.repository.SimilarVacanciesRepositoryImpl
import ru.practicum.android.diploma.features.similar.domain.impl.GetSimilarVacanciesUseCaseImpl
import ru.practicum.android.diploma.features.similar.domain.repository.SimilarVacanciesRepository
import ru.practicum.android.diploma.features.similar.domain.usecases.GetSimilarVacanciesUseCase
import ru.practicum.android.diploma.features.similar.presentation.SimilarVacanciesViewModel

val similarModule = module {

    viewModel {
        SimilarVacanciesViewModel(getSimilarVacanciesUseCase = get())
    }

    single<GetSimilarVacanciesUseCase> {
        GetSimilarVacanciesUseCaseImpl(similarVacanciesRepository = get())
    }

    single<SimilarVacanciesRepository> {
        SimilarVacanciesRepositoryImpl(similarVacanciesApi = get())
    }

    single<SimilarVacanciesApi> {
        provideSimilarVacanciesApi(retrofit = get())
    }
}

private fun provideSimilarVacanciesApi(retrofit: Retrofit) =
    retrofit.create(SimilarVacanciesApi::class.java)