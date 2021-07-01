package com.lush.spacex.rendering.interfaces

import androidx.lifecycle.LiveData
import com.lush.spacex.rendering.displayItems.RocketDisplayItem
import com.lush.spacex.rendering.displayItems.RocketsDisplayState

interface RocketListViewModel {
    val rocketsLoadState: LiveData<RocketsDisplayState>
    fun onRocketDisplayItemClicked(displayItem: RocketDisplayItem)
}
