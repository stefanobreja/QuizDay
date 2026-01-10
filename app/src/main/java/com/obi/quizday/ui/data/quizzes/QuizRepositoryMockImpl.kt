package com.obi.quizday.ui.data.quizzes

import com.obi.quizday.ui.data.Response
import com.obi.quizday.ui.data.Response.Success
import com.obi.quizday.ui.domain.Quiz
import com.obi.quizday.ui.domain.QuizRepository
import com.obi.quizday.ui.domain.QuizType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object QuizRepositoryMockImpl : QuizRepository {
    val quiz = Quiz(
        category = "Entertainment: Music",
        difficulty = "hard",
        question = "What was the name of the rock band that Nobuo Uematsu formed that played songs from various Final Fantasy games?",
        correctAnswer = "The Black Mages",
        incorrectAnswers = listOf(
            "The Final Fantasies",
            "The Espers",
            "The Rock Summoners"
        ),
        type = QuizType.MULTIPLE_CHOICE
    )

    override fun getTodayQuiz(): Flow<Response<Quiz>> {
        return flowOf(
            Success(
                quiz
            )
        )
    }
}