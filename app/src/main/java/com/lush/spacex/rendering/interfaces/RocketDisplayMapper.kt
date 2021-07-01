package com.lush.spacex.rendering.interfaces

import com.lush.spacex.persistance.entities.rocket.RocketEntity
import com.lush.spacex.rendering.displayItems.RocketsDisplayState

interface RocketDisplayMapper {
    fun mapToLoadingState(): RocketsDisplayState.Loading
    fun mapToErrorState(): RocketsDisplayState.Failed
    fun mapToLoadedState(rockets: List<RocketEntity>): RocketsDisplayState.Success
}