package com.obi.quizday.ui.data.quizzes

import retrofit2.http.GET

interface QuizService {
    @GET("api.php?amount=1")
    suspend fun getQuizzes(): QuizResponseDto
}