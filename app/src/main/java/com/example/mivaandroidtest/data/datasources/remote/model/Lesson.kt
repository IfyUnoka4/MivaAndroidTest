package com.example.mivaandroidtest.data.datasources.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val title: String,
    val videoUrl: String
)
