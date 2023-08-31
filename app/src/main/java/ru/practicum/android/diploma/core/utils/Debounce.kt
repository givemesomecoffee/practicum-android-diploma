package com.example.playlistmaker.util
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounce(delayMillis: Long,
                 coroutineScope: CoroutineScope,
                 cancelLast: Boolean,
                 action: (T) -> Unit): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (cancelLast) {
            debounceJob?.cancel()
        }
        if ((debounceJob?.isCompleted != false) || cancelLast) {
            debounceJob = coroutineScope.launch {
                delay(delayMillis)
                action(param)
            }
        }
    }
}