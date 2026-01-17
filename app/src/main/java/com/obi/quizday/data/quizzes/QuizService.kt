package com.obi.quizday.data.quizzes

import com.obi.quizday.data.quizzes.model.CategoriesResponseDto
import com.obi.quizday.data.quizzes.model.QuizResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizService {
    @GET("api.php")
    suspend fun getQuizzes(
        @Query("amount") amount: Int?,
        @Query("category") categoryId: Int?
        ): QuizResponseDto

    @GET("api_category.php")
    suspend fun getCategories(): CategoriesResponseDto
}