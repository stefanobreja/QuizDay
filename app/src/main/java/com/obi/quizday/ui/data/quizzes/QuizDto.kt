package com.obi.quizday.ui.data.quizzes

import com.google.gson.annotations.SerializedName
import com.obi.quizday.ui.domain.Quiz
import com.obi.quizday.ui.domain.QuizType

data class QuizDto(
    val category: String?,
    val difficulty: String?,
    val question: String?,
    @SerializedName("correct_answer") val correctAnswer: String?,
    @SerializedName("incorrect_answers") val incorrectAnswers: List<String>?,
    val type: String?
)

fun QuizDto.toDomain() = Quiz(
    category = category,
    difficulty = difficulty,
    question = question,
    correctAnswer = correctAnswer.orEmpty(),
    incorrectAnswers = incorrectAnswers.orEmpty(),
    type = type?.let { QuizType.fromValue(it) }
)
