package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class FirstStage(
    @Json(name = "thrust_sea_level")
    val thrustSeaLevel: ThrustDimension,
    @Json(name = "thrust_vacuum")
    val thrustVacuum: ThrustDimension,
    val reusable: Boolean,
    val engines: Int,
    @Json(name = "fuel_amount_tons")
    val fuelAmountTonnes: Double,
    @Json(name = "burn_time_sec")
    val burnTimeSeconds: Int?
)