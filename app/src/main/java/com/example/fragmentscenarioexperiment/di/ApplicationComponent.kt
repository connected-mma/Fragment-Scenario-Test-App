package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.MyApplication
import com.example.fragmentscenarioexperiment.di.scopes.ApplicationScope
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// Definition of the Application graph
@ApplicationScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class
])
interface ApplicationComponent: AndroidInjector<MyApplication> {
    fun build(): ApplicationComponent
}