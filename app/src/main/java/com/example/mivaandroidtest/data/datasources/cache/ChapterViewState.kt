package com.example.mivaandroidtest.data.datasources.cache

data class ChapterViewState(
    val isLoading: Boolean = false,
    val chapters: List<MivaChapter>? = emptyList(),
    val errorMessage: String? = null
)