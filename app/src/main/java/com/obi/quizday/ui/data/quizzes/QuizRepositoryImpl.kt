package com.obi.quizday.ui.data.quizzes

import com.obi.quizday.ui.data.Response
import com.obi.quizday.ui.data.Response.Success
import com.obi.quizday.ui.domain.Quiz
import com.obi.quizday.ui.domain.QuizRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import logcat.logcat

class QuizRepositoryImpl(private val apiService: QuizService) : QuizRepository {
    override fun getTodayQuiz(): Flow<Response<Quiz>> = callbackFlow {
        try {
            val response = apiService.getQuizzes()
            val result = response.responseCode?.let { QuizResultCode.getByCode(it) }
            when (result) {
                QuizResultCode.SUCCESS -> trySend(Success(response .results?.firstOrNull()?.toDomain()))
                QuizResultCode.NO_RESULTS -> trySend(Response.Error(Exception("NO_RESULT")))
                QuizResultCode.INVALID_PARAMETER -> trySend(Response.Error(Exception("INVALID_PARAMETER")))
                QuizResultCode.TOKEN_NOT_FOUND -> trySend(Response.Error(Exception("TOKEN_NOT_FOUND")))
                QuizResultCode.TOKEN_EMPTY -> trySend(Response.Error(Exception("TOKEN_EMPTY")))
                QuizResultCode.RATE_LIMIT_TOO_MANY_REQUESTS -> trySend(Response.Error(Exception("RATE_LIMIT_TOO_MANY_REQUESTS")))
                QuizResultCode.UNKNOWN -> trySend(Response.Error(Exception("UNKNOWN")))
                null -> trySend(Response.Error(Exception("UNKNOWN")))
            }
        } catch (e: Exception) {
            logcat { "getQuizzes failed with ${e.message}" }
            trySend(Response.Error(e))
            close(e)
        }
        awaitClose()
    }
}