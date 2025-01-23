package com.example.mivaandroidtest.domain.usecase

import com.example.mivaandroidtest.data.datasources.remote.model.Chapter
import com.example.mivaandroidtest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetChapterUseCase {
   suspend fun getChapters(): Flow<Resource<List<Chapter>>>
}