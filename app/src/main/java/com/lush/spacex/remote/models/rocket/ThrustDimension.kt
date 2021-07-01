package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep

@Keep
data class ThrustDimension(
    val kN: Int,
    val lbf: Int
)