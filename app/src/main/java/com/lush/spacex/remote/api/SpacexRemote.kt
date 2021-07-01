package com.lush.spacex.remote.api

import com.lush.spacex.remote.models.launch.Launch
import com.lush.spacex.remote.models.rocket.RocketDetail
import com.lush.spacex.remote.services.SpacexService
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit

class SpacexRemote(
    private val retrofit: Retrofit
) {
    suspend fun fetchRockets() : Result<List<RocketDetail>> {
        val spacexService = retrofit.create(SpacexService::class.java)
        return getResponse {
            spacexService.rockets()
        }
    }

    suspend fun fetchPastLaunches() : Result<List<Launch>> {
        val spacexService = retrofit.create(SpacexService::class.java)
        return getResponse {
            spacexService.pastLaunches()
        }
    }

    suspend fun fetchFutureLaunches() : Result<List<Launch>> {
        val spacexService = retrofit.create(SpacexService::class.java)
        return getResponse {
            spacexService.futureLaunches()
        }
    }

    private suspend fun <T> getResponse(request: suspend () -> Response<T>) : Result<T> {
        return try {
            val result = request.invoke()
            if (result.isSuccessful) {
                result.body()?.let {
                    Result.success(it)
                } ?: run {
                    Result.failure(
                        HttpException(result)
                    )
                }
            } else {
                Result.failure(
                    HttpException(result)
                )
            }
        } catch (ex: Throwable) {
            Result.failure(ex)
        }
    }
}