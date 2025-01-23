package com.example.mivaandroidtest.data.datasources.di

import android.content.Context
import androidx.room.Room
import com.example.mivaandroidtest.data.datasources.cache.MivaDatabase
import com.example.mivaandroidtest.data.datasources.cache.dao.ChaptersDao
import com.example.mivaandroidtest.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideMivaDataBase(@ApplicationContext context: Context): MivaDatabase {
        return Room.databaseBuilder(context, MivaDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideMivaDao(mivaDatabase: MivaDatabase): ChaptersDao = mivaDatabase.chaptersDao()
}