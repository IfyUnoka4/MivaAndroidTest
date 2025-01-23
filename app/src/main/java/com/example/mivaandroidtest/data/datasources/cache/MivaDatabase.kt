package com.example.mivaandroidtest.data.datasources.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mivaandroidtest.data.datasources.cache.dao.ChaptersDao
import com.example.mivaandroidtest.utils.Converter

@Database(
    entities = [MivaChapter::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class MivaDatabase: RoomDatabase() {
    abstract fun chaptersDao() : ChaptersDao
}