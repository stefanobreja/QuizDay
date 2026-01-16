package com.obi.quizday.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obi.quizday.data.Response
import com.obi.quizday.domain.quizzez.QuizRepository
import com.obi.quizday.domain.quizzez.model.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {
    val todayQuiz: StateFlow<Response<Quiz>>
        field : MutableStateFlow<Response<Quiz>> = MutableStateFlow(Response.Limbo)

    init {
        getQuizzes()
    }

    fun getQuizzes() {
        viewModelScope.launch {
            todayQuiz.update { Response.Loading }
            repository.getTodayQuiz().collect { response ->
                todayQuiz.update { response }
            }
        }
    }
}