package com.lush.spacex.rendering.displayItems

import java.util.*

sealed class RocketsDisplayState {
    data class Success(
        val items: List<RocketDisplayItem>
    ) : LaunchesDisplayState()

    object Loading : LaunchesDisplayState()

    object Failed : LaunchesDisplayState()
}

data class RocketDisplayItem(
    val id: String,
    val name: String,
    val launchDate: Date,
    val successRatePercentage: Int,
    val imageUrl: String
)