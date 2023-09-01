package ru.practicum.android.diploma.features.filter.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.practicum.android.diploma.features.filter.data.network.AreaApi
import ru.practicum.android.diploma.features.filter.domain.FilterRepository
import ru.practicum.android.diploma.features.filter.data.FilterRepositoryImpl
import ru.practicum.android.diploma.features.filter.domain.GetCachedFilterStateUseCase
import ru.practicum.android.diploma.features.filter.domain.SaveNewFilterUseCase
import ru.practicum.android.diploma.features.filter.domain.TrackFilterUseCase
import ru.practicum.android.diploma.features.filter.domain.impl.GetCachedFilterStateUseCaseImpl
import ru.practicum.android.diploma.features.filter.domain.impl.SaveNewFilterUseCaseImpl
import ru.practicum.android.diploma.features.filter.domain.impl.TrackFilterUseCaseImpl
import ru.practicum.android.diploma.features.filter.ui.screen.SettingsFilterViewModel

val filterModule = module {
    singleOf(::FilterRepositoryImpl).bind<FilterRepository>()
    singleOf(::TrackFilterUseCaseImpl).bind<TrackFilterUseCase>()
    singleOf(::GetCachedFilterStateUseCaseImpl).bind<GetCachedFilterStateUseCase>()
    singleOf(::SaveNewFilterUseCaseImpl).bind<SaveNewFilterUseCase>()
    viewModelOf(::SettingsFilterViewModel)
    single { provideAreaApi(get()) }
}


private fun provideAreaApi(retrofit: Retrofit) = retrofit.create(AreaApi::class.java)