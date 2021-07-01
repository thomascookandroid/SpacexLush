package com.lush.spacex.di

import com.lush.spacex.processing.interfaces.LaunchMapper
import com.lush.spacex.processing.interfaces.RocketMapper
import com.lush.spacex.processing.mappers.LaunchMapperImpl
import com.lush.spacex.processing.mappers.RocketMapperImpl
import com.lush.spacex.rendering.interfaces.LaunchDisplayMapper
import com.lush.spacex.rendering.interfaces.RocketDisplayMapper
import com.lush.spacex.rendering.mappers.LaunchDisplayMapperImpl
import com.lush.spacex.rendering.mappers.RocketDisplayMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
    @Provides
    @Singleton
    fun provideRocketMapper(): RocketMapper = RocketMapperImpl()

    @Provides
    @Singleton
    fun provideLaunchMapper(): LaunchMapper = LaunchMapperImpl()

    @Provides
    @Singleton
    fun provideRocketDisplayMapper(): RocketDisplayMapper = RocketDisplayMapperImpl()

    @Provides
    @Singleton
    fun provideLaunchDisplayMapper(): LaunchDisplayMapper = LaunchDisplayMapperImpl()
}