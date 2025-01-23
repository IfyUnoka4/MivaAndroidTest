package com.example.mivaandroidtest.data.datasources.di


import com.example.mivaandroidtest.data.repository.ChapterRepositoryImpl
import com.example.mivaandroidtest.domain.ChapterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsChapterRepository(
        chapterRepository: ChapterRepositoryImpl
    ) : ChapterRepository

}