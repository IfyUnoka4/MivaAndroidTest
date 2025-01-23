package com.example.mivaandroidtest.data.repository

import com.example.mivaandroidtest.data.ApiService
import com.example.mivaandroidtest.data.datasources.cache.dao.ChaptersDao
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock

class ChapterRepositoryImplTest {
    @Mock
    lateinit var mockApiService: ApiService

    @Mock
    lateinit var mockUserDao: ChaptersDao

    private lateinit var serviceUnderTest: ChapterRepositoryImpl

    @Test
    fun getChapters() {

    }
}