package com.example.mivaandroidtest.data.datasources.cache

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mivaandroidtest.data.datasources.remote.model.Chapter
import com.example.mivaandroidtest.data.datasources.remote.model.Lesson
import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

@JsonClass(generateAdapter = true)
@Entity(tableName = "chapter")
@Serializable
data class MivaChapter(
    @PrimaryKey val id: String,
    val title: String = "",
    val lessons: List<Lesson> = emptyList()
) {
    companion object {
        fun from(chapter: List<MivaChapter>): List<Chapter> {
            return chapter.map {
                Chapter(
                    title = it.title,
                    lessons = it.lessons

                )
            }
        }
    }

}
