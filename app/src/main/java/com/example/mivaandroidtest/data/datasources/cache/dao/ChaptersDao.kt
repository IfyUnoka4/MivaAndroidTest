package com.example.mivaandroidtest.data.datasources.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.mivaandroidtest.data.datasources.cache.MivaChapter
import com.example.mivaandroidtest.data.datasources.remote.model.Chapter

@Dao
interface ChaptersDao {
    @Transaction
    suspend fun updateChapters(user: List<MivaChapter>) {
        clearAllChapters()
        insertChapter(user)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChapter(chapters: List<MivaChapter>)

    @Query("SELECT * FROM chapter")
    suspend fun getChapters(): List<MivaChapter>

    @Query("DELETE FROM chapter")
    suspend fun clearAllChapters()


}