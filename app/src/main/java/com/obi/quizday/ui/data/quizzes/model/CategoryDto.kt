package com.obi.quizday.ui.data.quizzes.model

import com.obi.quizday.ui.domain.quizzez.model.Category

data class CategoryDto(
    val id: Int?,
    val name: String?
)

fun CategoryDto.toDomain() = Category(
    id = id,
    name = name
)
