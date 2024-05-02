package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.MyApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// Definition of the Application graph
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityModule::class,
    FragmentModule::class
])
interface ApplicationComponent: AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory: AndroidInjector.Factory<MyApplication>
}