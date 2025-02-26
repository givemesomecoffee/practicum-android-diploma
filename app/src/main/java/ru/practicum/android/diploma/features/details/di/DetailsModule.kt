package ru.practicum.android.diploma.features.details.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.practicum.android.diploma.features.details.data.network.VacancyApi
import ru.practicum.android.diploma.features.details.data.repository.VacancyRepositoryImpl
import ru.practicum.android.diploma.features.details.domain.usecases.GetVacancyUseCase
import ru.practicum.android.diploma.features.details.domain.repository.VacancyRepository
import ru.practicum.android.diploma.features.details.domain.impl.GetVacancyUseCaseImpl
import ru.practicum.android.diploma.features.details.domain.impl.ShareVacancyUseCaseImpl
import ru.practicum.android.diploma.features.details.domain.usecases.ShareVacancyUseCase
import ru.practicum.android.diploma.features.details.presentation.DetailsViewModel

val detailsModule = module {
    viewModel {
        DetailsViewModel(getVacancyUseCase = get(), shareVacancyUseCase = get())
    }

    single<GetVacancyUseCase> {
        GetVacancyUseCaseImpl(vacancyRepository = get())
    }

    single<ShareVacancyUseCase> {
        ShareVacancyUseCaseImpl(vacancyRepository = get())
    }

    single<VacancyRepository> {
        VacancyRepositoryImpl(vacancyApi = get(), context = get())
    }

    single<VacancyApi> {
        provideVacancyApi(retrofit = get())
    }
}

private fun provideVacancyApi(retrofit: Retrofit) = retrofit.create(VacancyApi::class.java)