package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class SpecificImpulseDimension(
    @Json(name = "sea_level")
    val seaLevel: Int,
    val vacuum: Int
)