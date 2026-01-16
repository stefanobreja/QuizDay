package com.obi.quizday.data.quizzes

import com.obi.quizday.data.quizzes.model.CategoriesResponseDto
import com.obi.quizday.data.quizzes.model.QuizResponseDto
import retrofit2.http.GET

interface QuizService {
    @GET("api.php?amount=1")
    suspend fun getQuizzes(): QuizResponseDto

    @GET("api_category.php")
    suspend fun getCategories(): CategoriesResponseDto
}