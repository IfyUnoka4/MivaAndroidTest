package com.example.mivaandroidtest.data.repository

import app.cash.turbine.test
import com.example.mivaandroidtest.data.ApiService
import com.example.mivaandroidtest.data.datasources.cache.dao.ChaptersDao
import com.example.mivaandroidtest.data.datasources.remote.model.Chapter
import com.example.mivaandroidtest.util.chapter
import com.example.mivaandroidtest.util.sampleData
import com.example.mivaandroidtest.utils.Resource
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

class ChapterRepositoryImplTest {
    @Mock
    lateinit var mockApiService: ApiService

    @Mock
    lateinit var mockChapterDao: ChaptersDao

    private lateinit var serviceUnderTest: ChapterRepositoryImpl

    val captor: ArgumentCaptor<Int> = ArgumentCaptor.forClass(Int::class.java)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        serviceUnderTest = ChapterRepositoryImpl(mockApiService, mockChapterDao)
    }

    @Test
    fun `when getChapters is called then return a successful list of chapters`() = runTest {
        val expectedData = Resource.Success(listOf(chapter))
        val userChapters = listOf(sampleData)

        whenever(mockChapterDao.getChapters()).thenReturn(userChapters)
        whenever(mockApiService.getChapters())
            .thenReturn(Response.success(listOf(chapter)))
        whenever(mockChapterDao.updateChapters(any())).thenReturn(Unit)

        val resultFlow = serviceUnderTest.getChapters()

        resultFlow.test {
            assertTrue(awaitItem() is Resource.Loading)
            assertTrue(awaitItem() is Resource.Success)
            cancelAndConsumeRemainingEvents()
        }

        val initialDbResult = mockChapterDao.getChapters()
        assertTrue(initialDbResult.isNotEmpty())
        assertEquals(1, initialDbResult.size)

        verify(mockApiService).getChapters()

        mockChapterDao.updateChapters(userChapters)
        val actualDbResult = mockChapterDao.getChapters()
        assertTrue(actualDbResult.isNotEmpty())
        assertEquals(expectedData.data?.first()?.title, actualDbResult.first().title)
    }

    @Test
    fun `when network call is made and has an error, then return appropriate error response`() =
        runTest {

            val errorText = "There is an error"
            val errorResponse = Response.error<List<Chapter>>(
                404,
                errorText.toResponseBody(null)
            )
            val dataList = listOf(sampleData)

            whenever(mockChapterDao.getChapters()).thenReturn(dataList)
            whenever(mockApiService.getChapters())
                .thenReturn(errorResponse)

            serviceUnderTest.getChapters().test {
                assertTrue(awaitItem() is Resource.Loading)
                assertTrue(awaitItem() is Resource.Error)
                cancelAndConsumeRemainingEvents()
            }

            verify(mockApiService).getChapters()
        }
}