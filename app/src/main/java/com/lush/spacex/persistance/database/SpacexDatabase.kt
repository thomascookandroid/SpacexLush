package com.lush.spacex.persistance.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lush.spacex.persistance.dao.LaunchDao
import com.lush.spacex.persistance.dao.RocketDao
import com.lush.spacex.persistance.entities.launch.LaunchEntity
import com.lush.spacex.persistance.entities.rocket.RocketEntity

@Database(
    entities = [RocketEntity::class, LaunchEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(com.lush.spacex.persistance.database.TypeConverters::class)
abstract class SpacexDatabase : RoomDatabase() {
    abstract fun rocketDao() : RocketDao
    abstract fun launchDao() : LaunchDao
}