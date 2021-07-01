package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@Keep
@JsonClass(generateAdapter = true)
data class RocketDetail(
    val height: SizeDimension,
    val diameter: SizeDimension,
    val mass: MassDimension,
    @Json(name = "first_stage")
    val firstStage: FirstStage,
    @Json(name = "second_stage")
    val secondStage: SecondStage,
    val engines: Engines,
    @Json(name = "landing_legs")
    val landingLegs: LandingLegs,
    @Json(name = "payload_weights")
    val payloadWeights: List<PayloadWeight>,
    @Json(name = "flickr_images")
    val imageUrls: List<String>,
    val name: String,
    val type: String,
    val active: Boolean,
    val stages: Int,
    val boosters: Int,
    @Json(name = "cost_per_launch")
    val costPerLaunch: Int,
    @Json(name = "success_rate_pct")
    val successRatePercent: Int,
    @Json(name = "first_flight")
    val firstFlight: Date,
    val country: String,
    val company: String,
    @Json(name = "wikipedia")
    val wikipediaUrl: String,
    val description: String,
    val id: String
)

