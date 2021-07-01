package com.lush.spacex.remote.models.launch

data class LaunchFailure(
    val time: Int,
    val altitude: Int?,
    val reason: String
)