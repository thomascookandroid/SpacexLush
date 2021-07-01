package com.lush.spacex.remote.models.launch

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@Keep
@JsonClass(generateAdapter = true)
data class Launch(
    @Json(name = "flight_number")
    val flightNumber: Int,
    val name: String,
    @Json(name = "date_utc")
    val date: Date,
    @Json(name = "static_fire_date_utc")
    val staticFireDate: Date?,
    val tbd: Boolean,
    val net: Boolean,
    val window: Int?,
    @Json(name = "rocket")
    val rocketId: String?,
    val success: Boolean?,
    val failures: List<LaunchFailure>,
    val upcoming: Boolean,
    val details: String?,
    val fairings: LaunchFairing?,
    @Json(name = "crew")
    val crewIds: List<String>,
    @Json(name = "ships")
    val shipIds: List<String>,
    @Json(name = "capsules")
    val capsuleIds: List<String>,
    @Json(name = "payloads")
    val payloadIds: List<String>,
    @Json(name = "launchpad")
    val launchPadId: String?,
    val cores: List<LaunchPadCore>,
    val links: LaunchPadLinks,
    @Json(name = "auto_update")
    val autoUpdate: Boolean
)