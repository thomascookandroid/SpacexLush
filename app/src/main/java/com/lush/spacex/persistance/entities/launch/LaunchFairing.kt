package com.lush.spacex.persistance.entities.launch

data class LaunchFairing(
    val reused: Boolean,
    val recoveryAttempt: Boolean,
    val recovered: Boolean,
    val shipIds: List<String>
)