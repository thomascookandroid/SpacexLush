package com.lush.spacex.rendering.mappers

import com.lush.spacex.persistance.entities.rocket.RocketEntity
import com.lush.spacex.rendering.displayItems.RocketDisplayItem
import com.lush.spacex.rendering.displayItems.RocketsDisplayState
import com.lush.spacex.rendering.interfaces.RocketDisplayMapper

class RocketDisplayMapperImpl : RocketDisplayMapper {
    override fun mapToLoadingState() = RocketsDisplayState.Loading

    override fun mapToErrorState() = RocketsDisplayState.Failed

    override fun mapToLoadedState(
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