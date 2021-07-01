package com.lush.spacex.persistance.entities.rocket

import androidx.room.Embedded

data class Engines(
    @Embedded(prefix = "_specificImpulse")
    val specificImpulse: SpecificImpulseDimension,
    @Embedded(prefix = "_thrustSeaLevel")
    val thrustSeaLevel: ThrustDimension,
    @Embedded(prefix = "_thrustVacuum")
    val thrustVacuum: ThrustDimension,
    val number: Int,
    val type: String,
    val version: String,
    val layout: String,
    val engineLossMax: Int,
    val propellantOne: String,
    val propellantTwo: String,
    val thrustToWeight: Float
)