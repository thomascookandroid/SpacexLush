package com.lush.spacex.processing.interfaces

import com.lush.spacex.persistance.entities.launch.LaunchEntity
import com.lush.spacex.processing.models.LoadingState
import kotlinx.coroutines.flow.Flow

interface LaunchInteractor {
    fun launchEntityLoad() : Flow<LoadingState<List<LaunchEntity>>>
}