package com.obi.quizday.ui.ui.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun QuizScreen(modifier: Modifier = Modifier, viewModel: QuizViewModel = hiltViewModel()) {
    val quizResponse by viewModel.todayQuiz.collectAsState()
    QuizView(modifier = modifier, response = quizResponse)
}