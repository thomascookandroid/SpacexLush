package com.lush.spacex.remote.models.launch

import com.squareup.moshi.Json

data class LaunchFairing(
    val reused: Boolean?,
    @Json(name = "recovery_attempt")
    val recoveryAttempt: Boolean?,
    val recovered: Boolean?,
    @Json(name = "ships")
    val shipIds: List<String>
)