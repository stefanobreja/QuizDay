package com.obi.quizday.ui.domain

import com.obi.quizday.ui.data.Response
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getTodayQuiz(): Flow<Response<Quiz>>
}