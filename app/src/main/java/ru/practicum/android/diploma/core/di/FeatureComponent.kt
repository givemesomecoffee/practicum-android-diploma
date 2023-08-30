package ru.practicum.android.diploma.core.di

import ru.practicum.android.diploma.features.details.di.detailsModule
import ru.practicum.android.diploma.features.favourites.di.favouritesModule
import ru.practicum.android.diploma.features.filter.di.filterModule
import ru.practicum.android.diploma.features.search.di.searchModule
import ru.practicum.android.diploma.features.team.di.teamModule

object FeatureComponent {
    val modules = listOf(
        filterModule,
        searchModule,
        detailsModule,
        teamModule,
        favouritesModule
    )
}