package com.lush.spacex.rendering.interfaces

import androidx.lifecycle.LiveData
import com.lush.spacex.rendering.displayItems.LaunchDisplayItem
import com.lush.spacex.rendering.displayItems.LaunchesDisplayState

interface LaunchListViewModel {
    val launchesLoadState: LiveData<LaunchesDisplayState>
    fun onLaunchDisplayItemClicked(displayItem: LaunchDisplayItem)
}