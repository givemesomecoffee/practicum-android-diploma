package ru.practicum.android.diploma

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.practicum.android.diploma.core.di.CoreComponent
import ru.practicum.android.diploma.core.di.FeatureComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(buildList {
                addAll(CoreComponent.modules)
                addAll(FeatureComponent.modules)
            })
        }
    }
}