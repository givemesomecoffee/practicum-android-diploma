package ru.practicum.android.diploma.core.data.network.interceptor

import okhttp3.Interceptor
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.core.data.network.NetworkConfig

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader(
                    NetworkConfig.AUTH_HEADER_NAME,
                    "${NetworkConfig.AUTH_TOKEN_PREFIX}${BuildConfig.HH_ACCESS_TOKEN}"
                )
                .addHeader(
                    NetworkConfig.USER_AGENT_HEADER_NAME,
                    NetworkConfig.USER_AGENT_INFO
                )
                .build()
        )
    }
}