package com.lush.spacex.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lush.spacex.persistance.entities.launch.LaunchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launch WHERE flightNumber = :flightNumber")
    fun getLaunch(flightNumber: Int) : Flow<LaunchEntity?>

    @Query("SELECT * FROM launch")
    fun getLaunches() : List<LaunchEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunch(launchEntity: LaunchEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLaunches(launchEntities: List<LaunchEntity>)
}