package com.lush.spacex.rendering.fragments.launchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lush.spacex.persistance.entities.launch.LaunchEntity
import com.lush.spacex.processing.interactors.LaunchInteractor
import com.lush.spacex.processing.models.LoadingState
import com.lush.spacex.rendering.displayItems.LaunchDisplayItem
import com.lush.spacex.rendering.mappers.LaunchDisplayMapper
import kotlinx.coroutines.flow.map

class LaunchListViewModel(
    private val interactor: LaunchInteractor,
    private val launchDisplayMapper: LaunchDisplayMapper
) : ViewModel() {

    val launchesLoadState = interactor.launchEntityLoad()
        .map { launchEntitiesLoadingState ->
            when (launchEntitiesLoadingState) {
                is LoadingState.Loading ->
                    launchDisplayMapper.mapToLoadingState()
                is LoadingState.Error ->
                    launchDisplayMapper.mapToErrorState()
                is LoadingState.Loaded<List<LaunchEntity>> ->
                    launchDisplayMapper.mapToLoadedState(launchEntitiesLoadingState.data)
            }
        }
        .asLiveData(viewModelScope.coroutineContext)

    fun onLaunchDisplayItemClicked(displayItem: LaunchDisplayItem) {

    }
}