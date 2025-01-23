package com.example.mivaandroidtest.data

import com.example.mivaandroidtest.data.datasources.remote.model.Chapter
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("chapters")
    suspend fun getChapters() : Response<List<Chapter>>
}