package com.example.mivaandroidtest.domain.usecase

import com.example.mivaandroidtest.data.datasources.remote.model.Chapter
import com.example.mivaandroidtest.domain.repository.ChapterRepository
import com.example.mivaandroidtest.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChapterUseCaseImpl @Inject constructor(
    private val chapterRepository: ChapterRepository
): GetChapterUseCase {

    override suspend fun getChapters(): Flow<Resource<List<Chapter>>> {
        return chapterRepository.getChapters()
    }
}