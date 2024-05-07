package com.example.fragmentscenarioexperiment.utils

import com.example.fragmentscenarioexperiment.di.scopes.FragmentScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class FragmentUtilsModule {

    @Binds
    @FragmentScope
    abstract fun bindsAnalyticsHandler(analyticsHandler: ExampleAnalyticsHandler): AnalyticsHandler
}