package com.lush.spacex.persistance.entities.rocket

import androidx.room.Embedded

data class FirstStage(
    @Embedded(prefix = "_thrustSeaLevel")
    val thrustSeaLevel: ThrustDimension,
    @Embedded(prefix = "_thrustVacuum")
    val thrustVacuum: ThrustDimension,
    val reusable: Boolean,
    val engines: Int,
    val fuelAmountTonnes: Double,
    val burnTimeSeconds: Int
)