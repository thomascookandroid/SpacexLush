package com.lush.spacex.persistance.entities.launch

import androidx.room.Embedded
import androidx.room.Entity
import java.util.*

@Entity(
    tableName = "launch",
    primaryKeys = ["flightNumber"]
)
data class LaunchEntity(
    val flightNumber: Int,
    val name: String,
    val date: Date,
    val staticFireDate: Date,
    val tbd: Boolean,
    val net: Boolean,
    val window: Int,
    val rocketId: String,
    val success: Boolean,
    val failures: List<LaunchFailure>,
    val upcoming: Boolean,
    val details: String,
    @Embedded(prefix = "_fairing")
    val fairings: LaunchFairing,
    val crewIds: List<String>,
    val shipIds: List<String>,
    val capsuleIds: List<String>,
    val payloadIds: List<String>,
    val launchPadId: String,
    val cores: List<LaunchPadCore>,
    @Embedded(prefix = "_links")
    val links: LaunchPadLinks,
    val autoUpdate: Boolean
)