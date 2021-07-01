package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep

@Keep
data class SizeDimension(
    val meters: Float?,
    val feet: Float?
)