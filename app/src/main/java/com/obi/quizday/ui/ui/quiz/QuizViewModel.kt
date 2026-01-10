package com.obi.quizday.ui.ui.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obi.quizday.ui.data.Response
import com.obi.quizday.ui.domain.Quiz
import com.obi.quizday.ui.domain.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class QuizViewModel @jakarta.inject.Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {
    val todayQuiz: StateFlow<Response<Quiz>>
        field : MutableStateFlow<Response<Quiz>> = MutableStateFlow(Response.Limbo)

    init {
        getQuizzes()
    }

    fun getQuizzes() {
        viewModelScope.launch {
            repository.getTodayQuiz().collect { response ->
                todayQuiz.update { response }
            }
        }
    }
}