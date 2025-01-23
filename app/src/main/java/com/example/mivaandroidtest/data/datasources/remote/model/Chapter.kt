package com.example.mivaandroidtest.data.datasources.remote.model

import com.example.mivaandroidtest.data.datasources.cache.MivaChapter
import kotlinx.serialization.Serializable

@Serializable
data class Chapter(
    val title: String = "",
    val lessons: List<Lesson> = emptyList()
){
    companion object {
        fun toMivaChapter(chapter: List<Chapter>): List<MivaChapter> {
            return chapter.map {
                MivaChapter(
                    title = it.title,
                    lessons = it.lessons,
                    id = ""
                )
            }
        }
    }
}
