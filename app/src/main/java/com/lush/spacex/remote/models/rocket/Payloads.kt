package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Payloads(
    @Json(name = "composite_fairing")
    val compositeFairing: CompositeFairing,
    @Json(name = "option_1")
    val optionOne: String
)