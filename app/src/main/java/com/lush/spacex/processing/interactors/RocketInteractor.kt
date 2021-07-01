package com.lush.spacex.processing.interactors

import android.util.Log
import com.lush.spacex.persistance.database.SpacexDatabase
import com.lush.spacex.persistance.entities.rocket.RocketEntity
import com.lush.spacex.processing.mappers.RocketDetailMapper
import com.lush.spacex.processing.models.LoadingState
import com.lush.spacex.remote.api.SpacexRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

private const val TAG = "RocketInteractor"

class RocketInteractor(
    private val spacexDatabase: SpacexDatabase,
    private val spacexRemote: SpacexRemote,
    private val rocketDetailMapper: RocketDetailMapper
) {
    fun fetchRocketList() : Flow<LoadingState<List<RocketEntity>>> {
        return flow {
            emit(LoadingState.Loading)

            val cachedRockets = spacexDatabase.rocketDao().getRockets() ?: emptyList()

            if (cachedRockets.isNotEmpty()) {
                emit(LoadingState.Loaded(cachedRockets))
            }

            val remoteResponse = spacexRemote.fetchRockets()
            when (remoteResponse.isSuccess) {
                true -> {
                    val remoteEntities = remoteResponse.getOrNull() ?: emptyList()
                    val entitiesToCache = remoteEntities.map { remoteRocket ->
                        rocketDetailMapper.mapRemoteModelToPersistenceModel(remoteRocket)
                    }

                    spacexDatabase.rocketDao().insertRockets(entitiesToCache)

                    emit(LoadingState.Loaded(entitiesToCache))
                }
                else -> {
                    Log.e(TAG, "Failed to fetch rockets from remote")
                    emit(LoadingState.Error)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}