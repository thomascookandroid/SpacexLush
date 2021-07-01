package com.lush.spacex.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lush.spacex.rendering.fragments.launchlist.LaunchListViewModelImpl
import com.lush.spacex.rendering.fragments.rocketdetail.RocketDetailViewModel
import com.lush.spacex.rendering.fragments.rocketlist.RocketListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RocketDetailViewModel::class)
    internal abstract fun bindRocketDetailViewModel(
        viewModel: RocketDetailViewModel
    ) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RocketListViewModelImpl::class)
    internal abstract fun bindRocketListViewModel(
        viewModel: RocketListViewModelImpl
    ) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchListViewModelImpl::class)
    internal abstract fun bindLaunchListViewModel(
        viewModel: LaunchListViewModelImpl
    ) : ViewModel
}