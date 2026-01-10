package com.obi.quizday.ui.domain

import com.google.gson.annotations.SerializedName

data class Quiz(
    val category: String?,
    val difficulty: String?,
    val question: String?,
    @SerializedName("correct_answer") val correctAnswer: String,
    @SerializedName("incorrect_answers") val incorrectAnswers: List<String>,
    val type: QuizType?
) {
    val answers = when (type) {
        QuizType.MULTIPLE_CHOICE -> incorrectAnswers.toMutableList().plus(correctAnswer).toList().shuffled()
        QuizType.BOOLEAN -> listOf(QuizType.BOOLEAN_YES, QuizType.BOOLEAN_NO)
        else -> listOf()
    }
}

enum class QuizType {
    MULTIPLE_CHOICE, BOOLEAN, UNKNOWN;

    companion object {
        const val BOOLEAN_YES = "Yes"
        const val BOOLEAN_NO = "No"
        fun fromValue(raw: String) = when (raw) {
            "multiple" -> MULTIPLE_CHOICE
            "boolean" -> BOOLEAN
            else -> UNKNOWN
        }
    }
}