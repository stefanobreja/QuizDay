package com.obi.quizday.ui.quiz

import android.os.CountDownTimer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obi.quizday.data.Response
import com.obi.quizday.domain.quizzez.QuizRepository
import com.obi.quizday.domain.quizzez.model.Answer
import com.obi.quizday.domain.quizzez.model.Quiz
import com.obi.quizday.domain.quizzez.model.Score
import com.obi.quizday.domain.quizzez.model.addPoints
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class QuizzesViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {
    val quizzes: StateFlow<Response<List<Quiz>>>
        field : MutableStateFlow<Response<List<Quiz>>> = MutableStateFlow(Response.Limbo)

    var currentQuizNo = mutableStateOf(1)
    val points = MutableStateFlow(Score())


    var timeLeft = mutableStateOf(COUNT_DOWN_TIME.toInt() / 1000)
    val countDownTimer = object : CountDownTimer(COUNT_DOWN_TIME, 1000) {
        override fun onFinish() {
            onNextQuestion()
            this.start()
        }

        override fun onTick(millisUntilFinished: Long) {
            timeLeft.value = if (millisUntilFinished > 0) {
                millisUntilFinished.toInt() / 1000
            } else {
                0
            }
        }
    }
    private val _events = Channel<Event>(Channel.BUFFERED)
    val events = _events.receiveAsFlow()


    fun onNextQuestion() {
        val quizzes = (quizzes.value as? Response.Success<List<Quiz>>)?.data.orEmpty()
        if (currentQuizNo.value < quizzes.size) {
            currentQuizNo.value++
        } else {
            _events.trySend(Event.Completed)
        }
    }

    fun onQuizAnswered(quiz: Quiz, answer: Answer) {
        countDownTimer.cancel()
        if (answer.isCorrect) {
            points.update {
                it.addPoints(quiz.difficulty, timeLeft.value)
            }
            onNextQuestion()
        } else {
            onNextQuestion()
        }
    }

    fun getQuizzes(categoryId: Int) {
        viewModelScope.launch {
            quizzes.update { Response.Loading }
            repository.getQuizzes(categoryId).collect { response ->
                quizzes.update { response }
            }
        }
    }

    sealed interface Event {
        object Completed : Event
    }

    companion object {
        const val COUNT_DOWN_TIME: Long = 30000
    }
}