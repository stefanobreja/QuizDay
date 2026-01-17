package com.obi.quizday.domain.quizzez.model

data class Score(
    val points: Int = 0
)

fun Score.addPoints(difficulty: Difficulty?, timeLeft: Int): Score {
    val difficultyPoints = difficulty?.points ?: 10
    val pointsByTime = timeLeft * difficultyPoints
    val newPoints = this.points + difficultyPoints + pointsByTime
    return Score(points = newPoints)
}

