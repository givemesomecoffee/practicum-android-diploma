package ru.practicum.android.diploma.core.di

import ru.practicum.android.diploma.core.data.di.dataModule
import ru.practicum.android.diploma.core.domain.di.domainModule

object CoreComponent {
    val modules = listOf(
        dataModule,
        domainModule
    )
}