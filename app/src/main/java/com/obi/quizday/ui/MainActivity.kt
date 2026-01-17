package com.obi.quizday.ui

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
import androidx.navigation.toRoute
import com.obi.quizday.ui.categories.CategoriesScreen
import com.obi.quizday.ui.navigation.Route
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
            navController = navController, startDestination = Route.Categories
        ) {
            composable<Route.Categories> {
                CategoriesScreen(modifier = modifier, navController = navController)
            }
            composable<Route.Quizzes> { backStackEntry ->
                val model: Route.Quizzes = backStackEntry.toRoute()
                QuizzesScreen(modifier = modifier, categoryId = model.categoryId)
            }
        }
    }
}
