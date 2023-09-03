package ru.practicum.android.diploma.core.ui.lce

import kotlinx.coroutines.CancellationException

fun <T> Result<T>.asState(): ContentState<T> {
    return if(isSuccess){
        ContentState(
            isLoading = false,
            content = getOrThrow()
        )
    } else {
        ContentState(
            isLoading = false,
            error = exceptionOrNull().takeIf { it !is CancellationException }
        )
    }
}