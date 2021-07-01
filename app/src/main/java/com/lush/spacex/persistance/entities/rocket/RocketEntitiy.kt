package com.lush.spacex.persistance.entities.rocket

import androidx.room.Embedded
import androidx.room.Entity
import java.util.*

@Entity(
    tableName = "rocket",
    primaryKeys = ["id"]
)
data class RocketEntity(
    val id: String,
    @Embedded(prefix = "_height")
    val height: SizeDimension,
    @Embedded(prefix = "_diameter")
    val diameter: SizeDimension,
    @Embedded(prefix = "_mass")
    val mass: MassDimension,
    @Embedded(prefix = "_firstStage")
    val firstStage: FirstStage,
    @Embedded(prefix = "_secondStage")
    val secondStage: SecondStage,
    @Embedded(prefix = "_engines")
    val engines: Engines,
    @Embedded(prefix = "_landingLegs")
    val landingLegs: LandingLegs,
    val payloadWeights: List<PayloadWeight>,
    val imageUrls: List<String>,
    val name: String,
    val type: String,
    val active: Boolean,
    val stages: Int,
    val boosters: Int,
    val costPerLaunch: Int,
    val successRatePercent: Int,
    val firstFlight: Date,
    val country: String,
    val company: String,
    val wikipediaUrl: String,
    val description: String
)