package com.lush.spacex.rendering.fragments.rocketlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lush.spacex.persistance.entities.rocket.RocketEntity
import com.lush.spacex.processing.interfaces.RocketInteractor
import com.lush.spacex.processing.models.LoadingState
import com.lush.spacex.rendering.displayItems.RocketDisplayItem
import com.lush.spacex.rendering.interfaces.RocketListViewModel
import com.lush.spacex.rendering.interfaces.RocketDisplayMapper
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RocketListViewModelImpl @Inject constructor(
    interactor: RocketInteractor,
    private val rocketEntityDisplayMapper: RocketDisplayMapper
) : ViewModel(), RocketListViewModel {

    override val rocketsLoadState = interactor.rocketListLoad()
        .map { rocketEntitiesLoadingState ->
            when (rocketEntitiesLoadingState) {
                is LoadingState.Loading ->
                    rocketEntityDisplayMapper.mapToLoadingState()
                is LoadingState.Error ->
                    rocketEntityDisplayMapper.mapToErrorState()
                is LoadingState.Loaded<List<RocketEntity>> ->
                    rocketEntityDisplayMapper.mapToLoadedState(rocketEntitiesLoadingState.data)
            }
        }
        .asLiveData(viewModelScope.coroutineContext)

    override fun onRocketDisplayItemClicked(displayItem: RocketDisplayItem) {

    }
}