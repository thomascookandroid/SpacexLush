package com.lush.spacex.di

import com.lush.spacex.rendering.fragments.rocketdetail.RocketDetailViewModel
import com.lush.spacex.persistance.database.SpacexDatabase
import com.lush.spacex.processing.interactors.LaunchInteractor
import com.lush.spacex.processing.interactors.RocketInteractor
import com.lush.spacex.processing.mappers.LaunchMapper
import com.lush.spacex.processing.mappers.RocketDetailMapper
import com.lush.spacex.remote.api.SpacexRemote
import com.lush.spacex.rendering.fragments.launchlist.LaunchListViewModel
import com.lush.spacex.rendering.fragments.rocketlist.RocketListViewModel
import com.lush.spacex.rendering.mappers.LaunchDisplayMapper
import com.lush.spacex.rendering.mappers.RocketEntityMapper
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
        rocketDetailMapper: RocketDetailMapper
    ) : RocketInteractor = RocketInteractor(
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
    ) : LaunchInteractor = LaunchInteractor(
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
        rocketEntityMapper: RocketEntityMapper
    ) : RocketListViewModel = RocketListViewModel(
        interactor,
        rocketEntityMapper
    )

    @Provides
    fun provideLaunchListViewModel(
        interactor: LaunchInteractor,
        launchDisplayMapper: LaunchDisplayMapper
    ) : LaunchListViewModel = LaunchListViewModel(
        interactor,
        launchDisplayMapper
    )
}