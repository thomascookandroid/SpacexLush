package com.lush.spacex.processing.interfaces

import com.lush.spacex.persistance.entities.launch.LaunchEntity
import com.lush.spacex.remote.models.launch.Launch

interface LaunchMapper {
    fun mapRemoteModelToPersistenceModel(launch: Launch) : LaunchEntity
}