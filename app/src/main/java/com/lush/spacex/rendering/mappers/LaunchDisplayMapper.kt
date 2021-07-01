package com.lush.spacex.rendering.mappers

import com.lush.spacex.persistance.entities.launch.LaunchEntity
import com.lush.spacex.rendering.displayItems.LaunchDisplayItem
import com.lush.spacex.rendering.displayItems.LaunchesDisplayState

class LaunchDisplayMapper {
    fun mapToLoadingState() = LaunchesDisplayState.Loading

    fun mapToErrorState() = LaunchesDisplayState.Failed

    fun mapToLoadedState(
        launches: List<LaunchEntity>
    ) = LaunchesDisplayState.Success(
        launches.map { launch ->
            LaunchDisplayItem(
                id = launch.flightNumber,
                name = launch.name,
                launchDate = launch.date,
                success = launch.success,
                imageUrl = launch.links.patch.small
            )
        }
    )
}