package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeExampleFragment(): ExampleFragment
}