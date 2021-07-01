package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep

@Keep
data class CompositeFairing(
    val height: SizeDimension,
    val diameter: SizeDimension
)