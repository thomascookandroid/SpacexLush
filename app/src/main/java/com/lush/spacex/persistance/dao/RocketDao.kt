package com.lush.spacex.persistance.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lush.spacex.persistance.entities.rocket.RocketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketDao {
    @Query("SELECT * FROM rocket WHERE id = :id")
    fun getRocket(id: String) : Flow<RocketEntity?>

    @Query("SELECT * FROM rocket")
    fun getRockets() : List<RocketEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRocket(rocketEntity: RocketEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRockets(rocketEntities: List<RocketEntity>)
}