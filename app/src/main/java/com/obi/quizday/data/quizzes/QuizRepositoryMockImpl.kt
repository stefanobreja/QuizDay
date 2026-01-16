package com.obi.quizday.data.quizzes

import com.obi.quizday.data.Response
import com.obi.quizday.data.Response.Success
import com.obi.quizday.domain.quizzez.QuizRepository
import com.obi.quizday.domain.quizzez.model.Answer
import com.obi.quizday.domain.quizzez.model.Category
import com.obi.quizday.domain.quizzez.model.Quiz
import com.obi.quizday.domain.quizzez.model.QuizType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object QuizRepositoryMockImpl : QuizRepository {
    val quiz = Quiz(
        category = "Entertainment: Music",
        difficulty = "hard",
        question = "What was the name of the rock band that Nobuo Uematsu formed that played songs from various Final Fantasy games?",
        answers = listOf(
            Answer("The Black Mages", true),
            Answer("The Final Fantasies", false),
        ),
        type = QuizType.MULTIPLE_CHOICE
    )

    val categories = listOf(
        Category(9, "General Knowledge"),
        Category(10, "Entertainment: Books"),
        Category(11, "Entertainment: Film"),
        Category(12, "Entertainment: Music"),
        Category(13, "Entertainment: Musicals & Theatres"),
        Category(14, "Entertainment: Television"),
        Category(15, "Entertainment: Video Games"),
        Category(16, "Entertainment: Board Games"),
        Category(17, "Science & Nature"),
        Category(18, "Science: Computers"),
        Category(19, "Science: Mathematics"),
        Category(20, "Mythology"),
        Category(21, "Sports"),
        Category(22, "Geography"),
        Category(23, "History"),
        Category(24, "Politics"),
        Category(25, "Art"),
        Category(26, "Celebrities"),
        Category(27, "Animals"),
        Category(28, "Vehicles"),
        Category(29, "Entertainment: Comics"),
        Category(30, "Science: Gadgets"),
        Category(31, "Entertainment: Japanese Anime & Manga"),
        Category(32, "Entertainment: Cartoon & Animations")
    )
    override fun getTodayQuiz(): Flow<Response<Quiz>> =
        flowOf(Success(quiz))

    override fun getCategories(): Flow<Response<List<Category>>> =
        flowOf(Success(categories))
}