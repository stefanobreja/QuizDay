package com.obi.quizday.di

import com.obi.quizday.data.quizzes.QuizRepositoryImpl
import com.obi.quizday.data.quizzes.QuizRepositoryMockImpl
import com.obi.quizday.data.quizzes.QuizService
import com.obi.quizday.domain.quizzez.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    private const val MOCK_ENABLED = true

    @Provides
    @Singleton
    fun provideQuizRepository(quizService: QuizService): QuizRepository {
        return if (MOCK_ENABLED) QuizRepositoryMockImpl
        else QuizRepositoryImpl(quizService)
    }

}