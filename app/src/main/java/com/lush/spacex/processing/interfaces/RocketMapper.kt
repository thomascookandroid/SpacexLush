package com.lush.spacex.processing.interfaces

import com.lush.spacex.persistance.entities.rocket.RocketEntity
import com.lush.spacex.remote.models.rocket.RocketDetail

interface RocketMapper{
    fun mapRemoteModelToPersistenceModel(rocket: RocketDetail) : RocketEntity
}