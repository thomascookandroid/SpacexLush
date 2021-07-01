package com.lush.spacex.persistance.entities.launch

data class LaunchPadCore(
    val coreId: String,
    val flight: Int,
    val gridFins: Boolean,
    val legs: Boolean,
    val reused: Boolean,
    val landingAttempt: Boolean,
    val landingSuccess: Boolean,
    val landingType: String,
    val landingPadId: String
)