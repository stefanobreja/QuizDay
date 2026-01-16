package com.obi.quizday.ui.domain.quizzez

import com.obi.quizday.ui.data.Response
import com.obi.quizday.ui.domain.quizzez.model.Category
import com.obi.quizday.ui.domain.quizzez.model.Quiz
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getTodayQuiz(): Flow<Response<Quiz>>

    fun getCategories(): Flow<Response<List<Category>>>
}