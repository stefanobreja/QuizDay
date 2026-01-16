package com.obi.quizday.data.quizzes.model

import com.google.gson.annotations.SerializedName

data class CategoriesResponseDto(
    @SerializedName("trivia_categories")
    val triviaCategories: List<CategoryDto>
)