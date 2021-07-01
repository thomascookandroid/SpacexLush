package com.lush.spacex.di

import com.lush.spacex.application.SpaceXApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidComponentsBuilderModule::class,
        RepoModule::class,
        RemoteModule::class,
        MapperModule::class,
        ViewModelModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: SpaceXApp) : Builder
        fun build() : AppComponent
    }

    fun inject(application: SpaceXApp)
}