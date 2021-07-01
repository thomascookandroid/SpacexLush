package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep

@Keep
data class LandingLegs(
    val number: Int,
    val material: String?
)