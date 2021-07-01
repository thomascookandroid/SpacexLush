package com.lush.spacex.persistance.entities.rocket

import androidx.room.Embedded

data class CompositeFairing(
    @Embedded(prefix = "_compositeHeight")
    val height: SizeDimension,
    @Embedded(prefix = "_compositeDiameter")
    val diameter: SizeDimension
)