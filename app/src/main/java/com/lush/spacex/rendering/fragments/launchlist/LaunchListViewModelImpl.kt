package com.lush.spacex.rendering.fragments.launchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lush.spacex.persistance.entities.launch.LaunchEntity
import com.lush.spacex.processing.interfaces.LaunchInteractor
import com.lush.spacex.processing.models.LoadingState
import com.lush.spacex.rendering.displayItems.LaunchDisplayItem
import com.lush.spacex.rendering.interfaces.LaunchListViewModel
import com.lush.spacex.rendering.interfaces.LaunchDisplayMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LaunchListViewModelImpl @Inject constructor(
    interactor: LaunchInteractor,
    private val launchDisplayDisplayMapper: LaunchDisplayMapper
) : ViewModel(), LaunchListViewModel {

    override val launchesLoadState = interactor.launchEntityLoad()
        .map { launchEntitiesLoadingState ->
            when (launchEntitiesLoadingState) {
                is LoadingState.Loading ->
                    launchDisplayDisplayMapper.mapToLoadingState()
                is LoadingState.Error ->
                    launchDisplayDisplayMapper.mapToErrorState()
                is LoadingState.Loaded<List<LaunchEntity>> ->
                    launchDisplayDisplayMapper.mapToLoadedState(launchEntitiesLoadingState.data)
            }
        }
        .asLiveData(viewModelScope.coroutineContext)

    override fun onLaunchDisplayItemClicked(displayItem: LaunchDisplayItem) {

    }
}