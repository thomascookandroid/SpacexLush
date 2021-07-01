package com.lush.spacex.di

import com.lush.spacex.processing.mappers.LaunchMapper
import com.lush.spacex.processing.mappers.RocketDetailMapper
import com.lush.spacex.rendering.mappers.LaunchDisplayMapper
import com.lush.spacex.rendering.mappers.RocketEntityMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    fun provideRocketDetailMapper(): RocketDetailMapper = RocketDetailMapper()

    @Provides
    @Singleton
    fun provideLaunchMapper(): LaunchMapper = LaunchMapper()

    @Provides
    @Singleton
    fun provideRocketEntityMapper(): RocketEntityMapper = RocketEntityMapper()

    @Provides
    @Singleton
    fun provideLaunchEntityMapper(): LaunchDisplayMapper = LaunchDisplayMapper()
}