package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.di.scopes.FragmentScope
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import com.example.fragmentscenarioexperiment.utils.FragmentUtilsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector(
        modules = [
            FragmentUtilsModule::class
        ]
    )
    @FragmentScope
    abstract fun contributeExampleFragment(): ExampleFragment
}