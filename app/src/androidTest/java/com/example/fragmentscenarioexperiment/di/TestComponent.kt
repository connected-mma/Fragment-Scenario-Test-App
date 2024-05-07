package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.di.scopes.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjectionModule

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
    ]
)
interface TestComponent