package com.example.mivaandroidtest.data.repository

import com.example.mivaandroidtest.data.ApiService
import com.example.mivaandroidtest.data.datasources.cache.MivaChapter
import com.example.mivaandroidtest.data.datasources.cache.dao.ChaptersDao
import com.example.mivaandroidtest.data.datasources.remote.model.Chapter
import com.example.mivaandroidtest.domain.repository.ChapterRepository
import com.example.mivaandroidtest.utils.Resource
import com.example.mivaandroidtest.utils.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChapterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val chaptersDao: ChaptersDao
): ChapterRepository {
    override suspend fun getChapters(): Flow<Resource<List<Chapter>>> {
        return flow {
            val localData = chaptersDao.getChapters()
            val localUserResult = MivaChapter.from(localData)

            emit(Resource.Loading(localUserResult))
            val remoteData = safeApiCall {
                apiService.getChapters()
            }
            when (remoteData) {
                is Resource.Success -> {
                    val mivaChapters = Chapter.toMivaChapter(remoteData.data.orEmpty())
                    chaptersDao.updateChapters(mivaChapters)

                    val userResult = MivaChapter.from(chaptersDao.getChapters())
                    emit(Resource.Success(userResult))
                }

                is Resource.Error -> {
                    emit(Resource.Error(remoteData.error, localUserResult))
                }

                else -> {
                    emit(Resource.Loading())
                }
            }
        }
    }
}