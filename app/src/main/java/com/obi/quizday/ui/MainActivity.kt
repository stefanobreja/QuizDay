package com.obi.quizday.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.obi.quizday.domain.quizzez.model.Answer
import com.obi.quizday.ui.quiz.QuizScreenClickListener
import com.obi.quizday.ui.quiz.QuizViewModel
import com.obi.quizday.ui.quiz.QuizzesScreen
import com.obi.quizday.ui.theme.QuizDayTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizDayTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    QuizDayApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun QuizDayApp(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = "quizzes"
        ) {
            val viewModel: QuizViewModel by viewModels()
            val clickListener = object : QuizScreenClickListener {
                override fun onAnswerSelected(answer: Answer) {
                    if (answer.isCorrect) {
                        Toast.makeText(this@MainActivity, "success", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "fail", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            composable("quizzes") {
                QuizzesScreen(modifier = modifier, viewModel = viewModel, listener = clickListener)
            }
        }
    }
}
