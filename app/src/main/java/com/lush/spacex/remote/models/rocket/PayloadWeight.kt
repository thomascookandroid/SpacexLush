package com.lush.spacex.remote.models.rocket

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class PayloadWeight(
    val id: String,
    val name: String,
    val kg: Int,
    val lb: Int
)