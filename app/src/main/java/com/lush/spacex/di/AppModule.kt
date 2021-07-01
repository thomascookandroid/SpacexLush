package com.lush.spacex.di

import com.lush.spacex.rendering.fragments.rocketdetail.RocketDetailViewModel
import com.lush.spacex.persistance.database.SpacexDatabase
import com.lush.spacex.processing.interactors.LaunchInteractorImpl
import com.lush.spacex.processing.interactors.RocketInteractorImpl
import com.lush.spacex.processing.interfaces.LaunchInteractor
import com.lush.spacex.processing.interfaces.LaunchMapper
import com.lush.spacex.processing.interfaces.RocketInteractor
import com.lush.spacex.processing.interfaces.RocketMapper
import com.lush.spacex.remote.interfaces.SpacexRemote
import com.lush.spacex.rendering.fragments.launchlist.LaunchListViewModelImpl
import com.lush.spacex.rendering.fragments.rocketlist.RocketListViewModelImpl
import com.lush.spacex.rendering.interfaces.LaunchDisplayMapper
import com.lush.spacex.rendering.interfaces.LaunchListViewModel
import com.lush.spacex.rendering.interfaces.RocketDisplayMapper
import com.lush.spacex.rendering.interfaces.RocketListViewModel
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideRocketInteractor(
        spacexDatabase: SpacexDatabase,
        spacexRemote: SpacexRemote,
        rocketDetailMapper: RocketMapper
    ) : RocketInteractor = RocketInteractorImpl(
        spacexDatabase,
        spacexRemote,
        rocketDetailMapper
    )

    @Provides
    @Singleton
    fun provideLaunchInteractor(
        spacexDatabase: SpacexDatabase,
        spacexRemote: SpacexRemote,
        launchMapper: LaunchMapper
    ) : LaunchInteractor = LaunchInteractorImpl(
        spacexDatabase,
        spacexRemote,
        launchMapper
    )

    @Provides
    fun provideRocketDetailViewModel(
        interactor: RocketInteractor
    ) : RocketDetailViewModel = RocketDetailViewModel(
        interactor
    )

    @Provides
    fun provideRocketListViewModel(
        interactor: RocketInteractor,
        rocketEntityMapper: RocketDisplayMapper
    ) : RocketListViewModel = RocketListViewModelImpl(
        interactor,
        rocketEntityMapper
    )

    @Provides
    fun provideLaunchListViewModel(
        interactor: LaunchInteractor,
        launchDisplayMapper: LaunchDisplayMapper
    ) : LaunchListViewModel = LaunchListViewModelImpl(
        interactor,
        launchDisplayMapper
    )
}