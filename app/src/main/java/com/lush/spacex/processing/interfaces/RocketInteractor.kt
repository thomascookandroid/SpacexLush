package com.lush.spacex.processing.interfaces

import com.lush.spacex.persistance.entities.rocket.RocketEntity
import com.lush.spacex.processing.models.LoadingState
import kotlinx.coroutines.flow.Flow

interface RocketInteractor {
    fun rocketListLoad() : Flow<LoadingState<List<RocketEntity>>>
}
