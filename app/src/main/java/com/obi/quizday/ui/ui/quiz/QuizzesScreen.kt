package com.obi.quizday.ui.ui.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.obi.quizday.ui.data.Response
import com.obi.quizday.ui.domain.quizzez.model.Answer
import com.obi.quizday.ui.domain.quizzez.model.Quiz


interface QuizScreenClickListener {
    fun onAnswerSelected(answer: Answer)

    companion object {
        val mocked = object : QuizScreenClickListener {
            override fun onAnswerSelected(answer: Answer) {}
        }
    }
}

@Composable
fun QuizzesScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizViewModel,
    listener: QuizScreenClickListener
) {
    val quizResponse by viewModel.todayQuiz.collectAsState()
    when (quizResponse) {
        is Response.Error -> {

        }

        Response.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp)
                )
            }
        }

        is Response.Success<Quiz> -> {
            val response = quizResponse as Response.Success<Quiz>
            response.data?.let {
                QuizView(
                    modifier = modifier,
                    quiz = it,
                    questionNumber = 1,
                    totalQuestionsNumber = 10,
                    listener = listener
                )
            }
        }

        else -> {
            // no-op
        }
    }
}