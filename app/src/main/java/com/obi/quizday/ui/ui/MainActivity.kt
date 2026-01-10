package com.obi.quizday.ui.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.obi.quizday.ui.ui.quiz.QuizScreen
import com.obi.quizday.ui.ui.theme.PurpleBackground
import com.obi.quizday.ui.ui.theme.QuizDayTheme
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
            composable("quizzes") {
                QuizScreen(modifier = modifier)
            }
        }
    }
}
