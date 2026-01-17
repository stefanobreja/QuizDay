package com.obi.quizday.data.quizzes

import com.obi.quizday.data.Response
import com.obi.quizday.data.Response.Error
import com.obi.quizday.data.Response.Success
import com.obi.quizday.data.quizzes.model.CategoryDto
import com.obi.quizday.data.quizzes.model.QuizResultCode
import com.obi.quizday.data.quizzes.model.toDomain
import com.obi.quizday.domain.quizzez.QuizRepository
import com.obi.quizday.domain.quizzez.model.Category
import com.obi.quizday.domain.quizzez.model.Quiz
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import logcat.logcat

class QuizRepositoryImpl(private val apiService: QuizService) : QuizRepository {
    override fun getTodayQuiz(): Flow<Response<Quiz>> = flow {
        try {
            emit(Response.Loading)

            val response = apiService.getQuizzes(amount = 1, categoryId = null)
            val result = response.responseCode?.let { QuizResultCode.getByCode(it) }
            val output = when (result) {
                QuizResultCode.SUCCESS -> {
                    val domainList = response.results?.first()?.toDomain()
                    Success(domainList)
                }

                QuizResultCode.RATE_LIMIT_TOO_MANY_REQUESTS -> Error(Exception("RATE_LIMIT"))
                QuizResultCode.NO_RESULTS -> Error(Exception("NO_RESULT"))
                QuizResultCode.INVALID_PARAMETER -> Error(Exception("INVALID_PARAMETER"))
                QuizResultCode.TOKEN_NOT_FOUND -> Error(Exception("TOKEN_NOT_FOUND"))
                QuizResultCode.TOKEN_EMPTY -> Error(Exception("TOKEN_EMPTY"))
                QuizResultCode.UNKNOWN -> Error(Exception("UNKNOWN"))
                else -> Error(Exception(result?.name ?: "UNKNOWN"))
            }
            emit(output)

        } catch (e: Exception) {
            logcat { "getTodayQuiz failed with: ${e.message}" }
            emit(Error(e))
        }
    }

    override fun getQuizzes(categoryId: Int): Flow<Response<List<Quiz>>> = flow {
        try {
            emit(Response.Loading)

            val response = apiService.getQuizzes(amount = 3, categoryId = categoryId)
            val result = response.responseCode?.let { QuizResultCode.getByCode(it) }
            val output = when (result) {
                QuizResultCode.SUCCESS -> {
                    val domainList = response.results?.map { it.toDomain() } ?: emptyList()
                    Success(domainList)
                }

                QuizResultCode.RATE_LIMIT_TOO_MANY_REQUESTS -> Error(Exception("RATE_LIMIT"))
                QuizResultCode.NO_RESULTS -> Error(Exception("NO_RESULT"))
                QuizResultCode.INVALID_PARAMETER -> Error(Exception("INVALID_PARAMETER"))
                QuizResultCode.TOKEN_NOT_FOUND -> Error(Exception("TOKEN_NOT_FOUND"))
                QuizResultCode.TOKEN_EMPTY -> Error(Exception("TOKEN_EMPTY"))
                QuizResultCode.UNKNOWN -> Error(Exception("UNKNOWN"))
                else -> Error(Exception(result?.name ?: "UNKNOWN"))
            }
            emit(output)
        } catch (e: Exception) {
            logcat { "getQuizzes failed with: ${e.message}" }
            emit(Error(e))
        }
    }

    override fun getCategories(): Flow<Response<List<Category>>> = flow {
        try {
            val response = apiService.getCategories()
            emit(Success(response.triviaCategories.map(CategoryDto::toDomain)))
        } catch (e: Exception) {
            logcat { "getCategories failed with ${e.message}" }
            emit(Error(e))
        }
    }
}