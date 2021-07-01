package com.lush.spacex.di

import com.lush.spacex.processing.mappers.LaunchMapper
import com.lush.spacex.processing.mappers.LaunchMapperImpl
import com.lush.spacex.processing.mappers.RocketDetailMapper
import com.lush.spacex.processing.mappers.RocketDetailMapperImpl
import com.lush.spacex.rendering.mappers.LaunchDisplayMapper
import com.lush.spacex.rendering.mappers.LaunchDisplayMapperImpl
import com.lush.spacex.rendering.mappers.RocketEntityMapper
import com.lush.spacex.rendering.mappers.RocketEntityMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    fun provideRocketDetailMapper(): RocketDetailMapper = RocketDetailMapperImpl()

    @Provides
    @Singleton
    fun provideLaunchMapper(): LaunchMapper = LaunchMapperImpl()

    @Provides
    @Singleton
    fun provideRocketEntityMapper(): RocketEntityMapper = RocketEntityMapperImpl()

    @Provides
    @Singleton
    fun provideLaunchEntityMapper(): LaunchDisplayMapper = LaunchDisplayMapperImpl()
}