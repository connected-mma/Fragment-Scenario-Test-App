package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.utils.ExampleFragmentDelegate
import com.example.fragmentscenarioexperiment.utils.Navigator
import dagger.Module
import dagger.Provides

@Module
class TestModule {
    @Provides
    fun provideExampleFragmentDelegate(): ExampleFragmentDelegate {
        // Provide a mock or fake implementation of ExampleFragmentDelegate
        return ExampleFragmentDelegate(Navigator())
    }
}