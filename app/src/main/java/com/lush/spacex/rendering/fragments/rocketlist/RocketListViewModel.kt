package com.lush.spacex.rendering.fragments.rocketlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lush.spacex.persistance.entities.rocket.RocketEntity
import com.lush.spacex.processing.interactors.RocketInteractor
import com.lush.spacex.processing.models.LoadingState
import com.lush.spacex.rendering.displayItems.RocketDisplayItem
import com.lush.spacex.rendering.mappers.RocketEntityMapper
import kotlinx.coroutines.flow.map

class RocketListViewModel(
    private val interactor: RocketInteractor,
    private val rocketEntityMapper: RocketEntityMapper
) : ViewModel() {

    val rocketsLoadState = interactor.fetchRocketList()
        .map { rocketEntitiesLoadingState ->
            when (rocketEntitiesLoadingState) {
                is LoadingState.Loading ->
                    rocketEntityMapper.mapToLoadingState()
                is LoadingState.Error ->
                    rocketEntityMapper.mapToErrorState()
                is LoadingState.Loaded<List<RocketEntity>> ->
                    rocketEntityMapper.mapToLoadedState(rocketEntitiesLoadingState.data)
            }
        }
        .asLiveData(viewModelScope.coroutineContext)

    fun onRocketDisplayItemClicked(displayItem: RocketDisplayItem) {

    }
}