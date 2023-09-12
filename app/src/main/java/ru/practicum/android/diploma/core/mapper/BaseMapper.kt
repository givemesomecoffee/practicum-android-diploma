package ru.practicum.android.diploma.core.mapper

abstract class BaseMapper<From, To> {
    abstract fun map(from: From): To
}