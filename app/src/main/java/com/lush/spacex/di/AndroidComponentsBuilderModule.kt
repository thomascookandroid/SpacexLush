package com.lush.spacex.di

import com.lush.spacex.rendering.fragments.launchlist.LaunchListFragment
import com.lush.spacex.rendering.fragments.rocketdetail.RocketDetailFragment
import com.lush.spacex.rendering.fragments.rocketlist.RocketListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidComponentsBuilderModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindRocketDetailFragment(): RocketDetailFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindRocketListFragment(): RocketListFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindLaunchListFragment(): LaunchListFragment
}