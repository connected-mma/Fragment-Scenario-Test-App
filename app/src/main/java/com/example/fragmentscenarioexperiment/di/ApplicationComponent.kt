package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.MyApplication
import com.example.fragmentscenarioexperiment.di.scopes.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

// Definition of the Application graph
@ApplicationScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    // FragmentModule::class,
])
interface ApplicationComponent: AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory: AndroidInjector.Factory<MyApplication>
}