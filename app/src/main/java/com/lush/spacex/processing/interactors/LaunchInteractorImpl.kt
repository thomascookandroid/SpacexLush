package com.lush.spacex.processing.interactors

import android.util.Log
import com.lush.spacex.persistance.database.SpacexDatabase
import com.lush.spacex.persistance.entities.launch.LaunchEntity
import com.lush.spacex.processing.interfaces.LaunchInteractor
import com.lush.spacex.processing.mappers.LaunchMapper
import com.lush.spacex.processing.models.LoadingState
import com.lush.spacex.remote.interfaces.SpacexRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

private const val TAG = "LaunchInteractorImpl"

class LaunchInteractorImpl(
    private val spacexDatabase: SpacexDatabase,
    private val spacexRemote: SpacexRemote,
    private val launchMapper: LaunchMapper
) : LaunchInteractor {
    override fun launchEntityLoad() : Flow<LoadingState<List<LaunchEntity>>> {
        return flow {
            emit(LoadingState.Loading)

            val cachedLaunches = spacexDatabase.launchDao().getLaunches() ?: emptyList()

            if (cachedLaunches.isNotEmpty()) {
                emit(LoadingState.Loaded(cachedLaunches))
            }

            val remoteResponse = spacexRemote.fetchPastLaunches()
            when (remoteResponse.isSuccess) {
                true -> {
                    val remoteEntities = remoteResponse.getOrNull() ?: emptyList()
                    val entitiesToCache = remoteEntities.map { remoteLaunch ->
                        launchMapper.mapRemoteModelToPersistenceModel(remoteLaunch)
                    }

                    spacexDatabase.launchDao().insertLaunches(entitiesToCache)

                    emit(LoadingState.Loaded(entitiesToCache))
                }
                else -> {
                    Log.e(TAG, "Failed to fetch launches from remote")
                    emit(LoadingState.Error)
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}