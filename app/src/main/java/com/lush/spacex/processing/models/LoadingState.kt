package com.lush.spacex.processing.models

sealed class LoadingState<out T> {
    object Loading : LoadingState<Nothing>()
    object Error : LoadingState<Nothing>()
    data class Loaded<T>(
        val data: T
    ) : LoadingState<T>()
}