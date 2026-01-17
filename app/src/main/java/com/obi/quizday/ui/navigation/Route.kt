package com.obi.quizday.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data class Quizzes(val categoryId: Int) : Route

    @Serializable
    object Categories : Route
}