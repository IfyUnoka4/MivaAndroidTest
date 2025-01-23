package com.example.mivaandroidtest.data.datasources.di

import com.example.mivaandroidtest.domain.ChapterRepository
import com.example.mivaandroidtest.domain.usecase.GetChapterUseCase
import com.example.mivaandroidtest.domain.usecase.GetChapterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetChapterUseCase(
        chapterRepository: ChapterRepository
    ): GetChapterUseCase = GetChapterUseCaseImpl(chapterRepository)
}