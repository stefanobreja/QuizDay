package com.obi.quizday.ui.data.quizzes.model

import com.google.gson.annotations.SerializedName
import com.obi.quizday.ui.domain.quizzez.model.Answer
import com.obi.quizday.ui.domain.quizzez.model.Quiz
import com.obi.quizday.ui.domain.quizzez.model.QuizType

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
                Answer(QuizType.BOOLEAN_YES, correctAnswer == QuizType.BOOLEAN_YES),
                Answer(QuizType.BOOLEAN_NO, correctAnswer == QuizType.BOOLEAN_NO)
            )
        }

        QuizType.UNKNOWN, null -> throw IllegalStateException("Type should not be unknown")
    }


    return Quiz(
        category = category,
        difficulty = difficulty,
        question = question,
        answers = answers,
        type = mappedType
    )
}
