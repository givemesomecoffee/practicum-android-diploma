package ru.practicum.android.diploma.core.data.network

object NetworkConfig {
    const val HOST = "https://api.hh.ru/"
    const val AUTH_HEADER_NAME = "Authorization"
    const val AUTH_TOKEN_PREFIX = "Bearer "
    const val USER_AGENT_HEADER_NAME = "HH-User-Agent"
    private const val APP_NAME_HEADER = "jobFinder-YaPracticum-team-2:3"
    private const val APP_EMAIL_HEADER = "igor.sheplyakov@yandex.ru"
    const val USER_AGENT_INFO = "$APP_NAME_HEADER ($APP_EMAIL_HEADER)"
}