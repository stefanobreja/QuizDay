package com.obi.quizday.data.quizzes.model

import com.google.gson.annotations.SerializedName
import com.obi.quizday.domain.quizzez.model.Answer
import com.obi.quizday.domain.quizzez.model.Difficulty
import com.obi.quizday.domain.quizzez.model.Quiz
import com.obi.quizday.domain.quizzez.model.QuizType

data class QuizDto(
    val category: String?,
    val difficulty: String?,
    val question: String?,
    @SerializedName("correct_answer") val correctAnswer: String?,
    @SerializedName("incorrect_answers") val incorrectAnswers: List<String>?,
    val type: String?
)

fun QuizDto.toDomain(): Quiz {
    if (correctAnswer == null || incorrectAnswers == null) throw IllegalStateException("Missing correct answer or incorrect answers")
    val mappedType = type?.let { QuizType.fromValue(it) }
    val answers = when (mappedType) {
        QuizType.MULTIPLE_CHOICE -> incorrectAnswers.map { Answer(it, false) } + Answer(correctAnswer, true)

        QuizType.BOOLEAN -> {
            listOf(
                Answer(QuizType.BOOLEAN_YES, true),
                Answer(QuizType.BOOLEAN_NO, false)
            )
        }

        QuizType.UNKNOWN, null -> throw IllegalStateException("Type should not be unknown")
    }
    val difficulty = when(difficulty){
        "easy" -> Difficulty.EASY
        "medium" -> Difficulty.MEDIUM
        "hard" -> Difficulty.HARD
        else -> null
    }
    return Quiz(
        category = category,
        difficulty = difficulty,
        question = question,
        answers = answers,
        type = mappedType
    )
}
