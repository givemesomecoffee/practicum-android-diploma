package ru.practicum.android.diploma.features.filter.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.practicum.android.diploma.features.filter.domain.FilterRepository
import ru.practicum.android.diploma.features.filter.data.FilterRepositoryImpl
import ru.practicum.android.diploma.features.filter.domain.TrackFilterUseCase
import ru.practicum.android.diploma.features.filter.domain.impl.TrackFilterUseCaseImpl

val filterModule = module {
    singleOf(::FilterRepositoryImpl).bind<FilterRepository>()
    singleOf(::TrackFilterUseCaseImpl).bind<TrackFilterUseCase>()
}