package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.ExampleFragmentModule
import com.example.fragmentscenarioexperiment.di.scopes.FragmentScope
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector(modules = [ExampleFragmentModule::class])
    @FragmentScope
    abstract fun contributeExampleFragment(): ExampleFragment
}