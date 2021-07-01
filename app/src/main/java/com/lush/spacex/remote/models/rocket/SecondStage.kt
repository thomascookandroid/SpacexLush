package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class SecondStage(
    val thrust: ThrustDimension,
    val payloads: Payloads,
    val reusable: Boolean,
    val engines: Int,
    @Json(name = "fuel_amount_tons")
    val fuelAmountTonnes: Double,
    @Json(name = "burn_time_sec")
    val burnTimeSeconds: Int?
)