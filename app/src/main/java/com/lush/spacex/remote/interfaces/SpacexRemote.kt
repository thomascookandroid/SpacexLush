package com.lush.spacex.remote.interfaces

import com.lush.spacex.remote.models.launch.Launch
import com.lush.spacex.remote.models.rocket.RocketDetail

interface SpacexRemote {
    suspend fun fetchRockets() : Result<List<RocketDetail>>
    suspend fun fetchPastLaunches() : Result<List<Launch>>
    suspend fun fetchFutureLaunches() : Result<List<Launch>>
}