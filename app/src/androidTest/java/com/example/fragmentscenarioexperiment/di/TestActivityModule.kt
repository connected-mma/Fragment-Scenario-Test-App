package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.TestActivity
import com.example.fragmentscenarioexperiment.di.scopes.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TestActivityModule {
    @ContributesAndroidInjector(
        modules = [FragmentModule::class]
    )
    @ActivityScope
    abstract fun contributeTestActivity(): TestActivity
}