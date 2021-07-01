package com.lush.spacex.rendering.displayItems

import java.util.*

sealed class RocketsDisplayState {
    data class Success(
        val items: List<RocketDisplayItem>
    ) : RocketsDisplayState()

    object Loading : RocketsDisplayState()

    object Failed : RocketsDisplayState()
}

data class RocketDisplayItem(
    val id: String,
    val name: String,
    val launchDate: Date,
    val successRatePercentage: Int,
    val imageUrl: String
)