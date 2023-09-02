package ru.practicum.android.diploma.core.data.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.core.data.network.NetworkConfig
import ru.practicum.android.diploma.core.data.network.interceptor.AuthInterceptor


val dataModule = module {
    single {
        androidContext()
            .getSharedPreferences("practicum_example_preferences", Context.MODE_PRIVATE)
    }
    single {


    }
    single {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(AuthInterceptor())
        if (BuildConfig.DEBUG) {
            clientBuilder.addInterceptor(resolveLoggingInterceptor())
        }
        clientBuilder.build()
    }
    single { provideRetrofit(get()) }
}

private fun provideRetrofit(client: OkHttpClient): Retrofit {
    val json = Json {
        ignoreUnknownKeys = true
    }
    return Retrofit.Builder()
        .baseUrl(NetworkConfig.HOST)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .build()
}

private fun resolveLoggingInterceptor(): Interceptor {
    val logging = HttpLoggingInterceptor()
    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
    return logging
}