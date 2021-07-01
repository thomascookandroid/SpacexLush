package com.lush.spacex.remote.services

import com.lush.spacex.remote.models.launch.Launch
import com.lush.spacex.remote.models.rocket.RocketDetail
import retrofit2.Response
import retrofit2.http.GET

interface SpacexService {
    @GET("/v4/rockets")
    suspend fun rockets() : Response<List<RocketDetail>>

    @GET("/v4/launches/past")
    suspend fun pastLaunches() : Response<List<Launch>>

    @GET("/v4/launches/upcoming")
    suspend fun futureLaunches() : Response<List<Launch>>
}