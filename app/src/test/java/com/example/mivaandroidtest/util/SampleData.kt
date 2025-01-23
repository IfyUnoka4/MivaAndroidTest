package com.example.mivaandroidtest.util

import com.example.mivaandroidtest.data.datasources.cache.MivaChapter
import com.example.mivaandroidtest.data.datasources.remote.model.Chapter
import com.example.mivaandroidtest.data.datasources.remote.model.Lesson

val sampleData = MivaChapter(
    id = "1",
    title = "Test 1",
    lessons = mutableListOf<Lesson>()
)

val chapter = Chapter(
    title = "Test 1",
    lessons = mutableListOf<Lesson>()
)