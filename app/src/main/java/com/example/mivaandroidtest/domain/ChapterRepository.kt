package com.example.mivaandroidtest.domain

import com.example.mivaandroidtest.data.datasources.remote.model.Chapter
import com.example.mivaandroidtest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ChapterRepository {
    suspend fun getChapters(): Flow<Resource<List<Chapter>>>
}