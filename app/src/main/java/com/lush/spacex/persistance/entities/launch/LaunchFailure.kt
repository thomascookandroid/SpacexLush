package com.lush.spacex.persistance.entities.launch

data class LaunchFailure(
    val time: Int,
    val altitude: Int,
    val reason: String
)