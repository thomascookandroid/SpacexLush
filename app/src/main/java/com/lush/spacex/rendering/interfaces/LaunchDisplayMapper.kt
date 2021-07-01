package com.lush.spacex.rendering.interfaces

import com.lush.spacex.persistance.entities.launch.LaunchEntity
import com.lush.spacex.rendering.displayItems.LaunchesDisplayState

interface LaunchDisplayMapper {
    fun mapToLoadingState(): LaunchesDisplayState.Loading
    fun mapToErrorState(): LaunchesDisplayState.Failed
    fun mapToLoadedState(launches: List<LaunchEntity>) : LaunchesDisplayState.Success
}