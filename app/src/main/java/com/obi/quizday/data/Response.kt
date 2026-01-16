package com.obi.quizday.data

sealed class Response<out T> {
    data object Limbo : Response<Nothing>()

    data object Loading : Response<Nothing>()

    data class Success<out T>(
        val data: T?
    ) : Response<T>()

    data class Error(
        val e: Exception?
    ) : Response<Nothing>()
}