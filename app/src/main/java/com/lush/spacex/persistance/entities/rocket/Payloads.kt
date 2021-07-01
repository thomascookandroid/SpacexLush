package com.lush.spacex.persistance.entities.rocket

import androidx.room.Embedded

data class Payloads(
    @Embedded(prefix = "_compositeFairing")
    val compositeFairing: CompositeFairing,
    val optionOne: String
)