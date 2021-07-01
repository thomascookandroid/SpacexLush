package com.lush.spacex.rendering.mappers

import com.lush.spacex.persistance.entities.rocket.RocketEntity
import com.lush.spacex.rendering.displayItems.RocketDisplayItem
import com.lush.spacex.rendering.displayItems.RocketsDisplayState

class RocketEntityMapper {
    fun mapToLoadingState() = RocketsDisplayState.Loading

    fun mapToErrorState() = RocketsDisplayState.Failed

    fun mapToLoadedState(
        rockets: List<RocketEntity>
    ) = RocketsDisplayState.Success(
        rockets.map { rocket ->
            RocketDisplayItem(
                id = rocket.id,
                name = rocket.name,
                launchDate = rocket.firstFlight,
                successRatePercentage = rocket.successRatePercent,
                imageUrl = rocket.imageUrls.firstOrNull() ?: ""
            )
        }
    )
}