package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Engines(
    @Json(name = "isp")
    val specificImpulse: SpecificImpulseDimension,
    @Json(name = "thrust_sea_level")
    val thrustSeaLevel: ThrustDimension,
    @Json(name = "thrust_vacuum")
    val thrustVacuum: ThrustDimension,
    val number: Int,
    val type: String,
    val version: String,
    val layout: String?,
    @Json(name = "engine_loss_max")
    val engineLossMax: Int?,
    @Json(name = "propellant_1")
    val propellantOne: String,
    @Json(name = "propellant_2")
    val propellantTwo: String,
    @Json(name = "thrust_to_weight")
    val thrustToWeight: Float
)