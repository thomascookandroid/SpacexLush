package com.lush.spacex.persistance.entities.rocket

data class PayloadWeight(
    val id: String,
    val name: String,
    val kg: Int,
    val lb: Int
)