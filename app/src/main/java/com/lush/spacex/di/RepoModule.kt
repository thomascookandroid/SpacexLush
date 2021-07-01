package com.lush.spacex.di

import androidx.room.Room
import com.lush.spacex.application.SpaceXApp
import com.lush.spacex.persistance.database.SpacexDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val ROCKET_DETAIL_DB_NAME = "Spacex.db"

@Module
class RepoModule {
    @Provides
    @Singleton
    fun provideRocketDatabase(
        application: SpaceXApp
    ): SpacexDatabase = Room
        .databaseBuilder(application, SpacexDatabase::class.java, ROCKET_DETAIL_DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
}