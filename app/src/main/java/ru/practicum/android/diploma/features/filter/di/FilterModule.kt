package ru.practicum.android.diploma.features.filter.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.practicum.android.diploma.features.filter.data.AreaRepositoryImpl
import ru.practicum.android.diploma.features.filter.data.network.AreaApi
import ru.practicum.android.diploma.features.filter.domain.FilterRepository
import ru.practicum.android.diploma.features.filter.data.FilterRepositoryImpl
import ru.practicum.android.diploma.features.filter.domain.AreaRepository
import ru.practicum.android.diploma.features.filter.domain.GetCachedFilterStateUseCase
import ru.practicum.android.diploma.features.filter.domain.GetCountriesUseCase
import ru.practicum.android.diploma.features.filter.domain.SaveNewFilterUseCase
import ru.practicum.android.diploma.features.filter.domain.TrackFilterUseCase
import ru.practicum.android.diploma.features.filter.domain.impl.GetCachedFilterStateUseCaseImpl
import ru.practicum.android.diploma.features.filter.domain.impl.GetCountriesUseCaseImpl
import ru.practicum.android.diploma.features.filter.domain.impl.SaveNewFilterUseCaseImpl
import ru.practicum.android.diploma.features.filter.domain.impl.TrackFilterUseCaseImpl
import ru.practicum.android.diploma.features.filter.ui.screen.country.CountryFilterViewModel
import ru.practicum.android.diploma.features.filter.ui.screen.settings.SettingsFilterViewModel
import ru.practicum.android.diploma.features.filter.ui.screen.workplace.WorkPlaceViewModel

val filterModule = module {
    singleOf(::FilterRepositoryImpl).bind<FilterRepository>()
    singleOf(::TrackFilterUseCaseImpl).bind<TrackFilterUseCase>()
    singleOf(::GetCachedFilterStateUseCaseImpl).bind<GetCachedFilterStateUseCase>()
    singleOf(::SaveNewFilterUseCaseImpl).bind<SaveNewFilterUseCase>()
    viewModelOf(::SettingsFilterViewModel)
    single { provideAreaApi(get()) }
    viewModelOf(::WorkPlaceViewModel)
    singleOf(::AreaRepositoryImpl).bind<AreaRepository>()
    viewModelOf(::CountryFilterViewModel)
    singleOf(::GetCountriesUseCaseImpl).bind<GetCountriesUseCase>()
}

private fun provideAreaApi(retrofit: Retrofit) = retrofit.create(AreaApi::class.java)