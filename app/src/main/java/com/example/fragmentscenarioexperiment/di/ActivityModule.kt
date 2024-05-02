package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}