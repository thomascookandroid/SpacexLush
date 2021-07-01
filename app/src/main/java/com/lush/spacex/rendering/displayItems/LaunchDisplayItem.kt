package com.lush.spacex.rendering.displayItems

import java.util.*

sealed class LaunchesDisplayState {
    data class Success(
        val items: List<LaunchDisplayItem>
    ) : LaunchesDisplayState()

    object Loading : LaunchesDisplayState()

    object Failed : LaunchesDisplayState()
}

data class LaunchDisplayItem(
    val id: Int,
    val name: String,
    val launchDate: Date,
    val success: Boolean,
    val imageUrl: String
)