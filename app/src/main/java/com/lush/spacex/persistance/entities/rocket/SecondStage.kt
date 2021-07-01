package com.lush.spacex.persistance.entities.rocket

import androidx.room.Embedded

data class SecondStage(
    @Embedded(prefix = "_thrust")
    val thrust: ThrustDimension,
    @Embedded(prefix = "_payloads")
    val payloads: Payloads,
    val reusable: Boolean,
    val engines: Int,
    val fuelAmountTonnes: Double,
    val burnTimeSeconds: Int
)