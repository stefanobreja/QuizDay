package com.obi.quizday.ui.quiz

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.obi.quizday.data.Response
import com.obi.quizday.domain.quizzez.model.Quiz


@Composable
fun QuizzesScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizzesViewModel = hiltViewModel(),
    categoryId: Int
) {
    val context = LocalContext.current
    val quizzesResponse by viewModel.quizzes.collectAsState()
    val currentQuizNo by viewModel.currentQuizNo
    val timeLeft by viewModel.timeLeft

    LaunchedEffect(Unit) {
        viewModel.getQuizzes(categoryId)
    }

    LaunchedEffect(Unit) {
        viewModel.events.collect {
            when (it) {
                QuizzesViewModel.Event.Completed -> {
                    Toast.makeText(context, "Points: ${viewModel.points.value}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    when (quizzesResponse) {
        is Response.Error -> {
            // TODO: implement error
        }

        Response.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp)
                )
            }
        }

        is Response.Success<List<Quiz>> -> {
            val response = quizzesResponse as Response.Success<List<Quiz>>
            val quizzes = response.data
            quizzes?.let {
                LaunchedEffect(Unit) {
                    viewModel.countDownTimer.start()
                }
                QuizView(
                    modifier = modifier,
                    quiz = quizzes[currentQuizNo-1],
                    questionNumber = currentQuizNo,
                    totalQuestionsNumber = quizzes.size,
                    onAnswerSelected = { answer ->
                        viewModel.onQuizAnswered(quizzes[currentQuizNo-1], answer)
                    },
                    timeLeft = timeLeft,
                )
            }
        }

        else -> {
            // no-op
        }
    }
}