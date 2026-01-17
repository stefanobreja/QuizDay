package com.obi.quizday.domain.quizzez.model

data class Quiz(
    val category: String?,
    val difficulty: Difficulty?,
    val question: String?,
    val answers: List<Answer>,
    val type: QuizType?
)

data class Answer(
    val text: String,
    val isCorrect: Boolean
)

enum class Difficulty(val points: Int) {
    EASY(5),
    MEDIUM(10),
    HARD(20)
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