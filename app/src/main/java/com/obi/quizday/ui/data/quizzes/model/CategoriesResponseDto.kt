package com.obi.quizday.ui.data.quizzes.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponseDto(
    @SerializedName("trivia_categories")
    val triviaCategories: List<CategoryDto>
)