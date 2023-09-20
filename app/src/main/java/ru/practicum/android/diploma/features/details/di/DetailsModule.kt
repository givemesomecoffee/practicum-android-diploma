package ru.practicum.android.diploma.features.details.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.practicum.android.diploma.features.details.data.mapper.VacancyMapper
import ru.practicum.android.diploma.features.details.data.network.VacancyApi
import ru.practicum.android.diploma.features.details.data.repository.VacancyRepositoryImpl
import ru.practicum.android.diploma.features.details.domain.impl.AddToFavoriteUseCaseImpl
import ru.practicum.android.diploma.features.details.domain.impl.CheckIsFavoriteVacancyUseCaseImpl
import ru.practicum.android.diploma.features.details.domain.impl.DeleteFromFavoriteUseCaseImpl
import ru.practicum.android.diploma.features.details.domain.impl.GetFromFavoriteUseCaseImpl
import ru.practicum.android.diploma.features.details.domain.impl.GetVacancyUseCaseImpl
import ru.practicum.android.diploma.features.details.domain.repository.VacancyRepository
import ru.practicum.android.diploma.features.details.domain.usecases.AddToFavoriteUseCase
import ru.practicum.android.diploma.features.details.domain.usecases.CheckIsFavoriteVacancyUseCase
import ru.practicum.android.diploma.features.details.domain.usecases.DeleteFromFavoriteUseCase
import ru.practicum.android.diploma.features.details.domain.usecases.GetFromFavoriteUseCase
import ru.practicum.android.diploma.features.details.domain.usecases.GetVacancyUseCase
import ru.practicum.android.diploma.features.details.presentation.DetailsViewModel

val detailsModule = module {
    viewModel {
        DetailsViewModel(
            checkIsFavoriteVacancyUseCase = get(),
            addToFavoriteUseCase = get(),
            deleteFromFavoriteUseCase = get(),
            getFromFavoriteUseCase = get(),
            getVacancyUseCase = get(),
            vacancyMapper = get(),
        )
    }

    single {
        VacancyMapper()
    }

    single<GetVacancyUseCase> {
        GetVacancyUseCaseImpl(vacancyRepository = get())
    }

    single<GetFromFavoriteUseCase> {
        GetFromFavoriteUseCaseImpl(favoriteVacanciesRepository = get())
    }

    single<CheckIsFavoriteVacancyUseCase> {
        CheckIsFavoriteVacancyUseCaseImpl(favoriteVacanciesRepository = get())
    }

    single<DeleteFromFavoriteUseCase> {
        DeleteFromFavoriteUseCaseImpl(favoriteVacanciesRepository = get())
    }

    single<AddToFavoriteUseCase> {
        AddToFavoriteUseCaseImpl(favoriteVacanciesRepository = get())
    }

    single<VacancyRepository> {
        VacancyRepositoryImpl(vacancyApi = get())
    }

    single<VacancyApi> {
        provideVacancyApi(retrofit = get())
    }
}

private fun provideVacancyApi(retrofit: Retrofit) = retrofit.create(VacancyApi::class.java)