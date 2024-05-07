package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.MainActivity
import com.example.fragmentscenarioexperiment.di.scopes.ActivityScope
import com.example.fragmentscenarioexperiment.utils.FragmentUtilsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(
        modules = [FragmentModule::class]
    )
    @ActivityScope
    abstract fun contributeMainActivity(): MainActivity
}