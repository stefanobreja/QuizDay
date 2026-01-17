package com.obi.quizday.ui.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obi.quizday.data.Response
import com.obi.quizday.domain.quizzez.QuizRepository
import com.obi.quizday.domain.quizzez.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: QuizRepository
) : ViewModel() {
    val categories: StateFlow<Response<List<Category>>>
        field : MutableStateFlow<Response<List<Category>>> = MutableStateFlow(Response.Limbo)

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            categories.update { Response.Loading }
            repository.getCategories().collect { response ->
                categories.update { response }
            }
        }
    }
}