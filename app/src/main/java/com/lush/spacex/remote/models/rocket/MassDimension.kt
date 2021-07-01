package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep

@Keep
data class MassDimension(
    val kg: Int,
    val lb: Int
)