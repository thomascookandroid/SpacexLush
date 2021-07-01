package com.lush.spacex.remote.models.launch

import com.squareup.moshi.Json

data class LaunchPadCore(
    @Json(name = "core")
    val coreId: String?,
    val flight: Int?,
    val gridFins: Boolean?,
    val legs: Boolean?,
    val reused: Boolean?,
    @Json(name = "landing_attempt")
    val landingAttempt: Boolean?,
    @Json(name = "landing_success")
    val landingSuccess: Boolean?,
    @Json(name = "landing_type")
    val landingType: String?,
    @Json(name = "landpad")
    val landingPadId: String?
)