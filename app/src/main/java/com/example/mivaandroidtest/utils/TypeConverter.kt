package com.example.mivaandroidtest.utils

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mivaandroidtest.data.datasources.remote.model.Lesson
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@TypeConverters
class Converter {
    private val moshi = Moshi.Builder()
        .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
        .build()


    private val listLessonType = Types.newParameterizedType(List::class.java, Lesson::class.java)
    private val listLessonAdapter = moshi.adapter<List<Lesson>>(listLessonType)

    @TypeConverter
    fun fromLessonsToString(value: List<Lesson>?): String? {
        return value?.let {
            listLessonAdapter.toJson(it)
        }
    }

    @TypeConverter
    fun fromStringToLessons(value: String?): List<Lesson>? {
        return value?.let {
            listLessonAdapter.fromJson(it)
        }
    }

}