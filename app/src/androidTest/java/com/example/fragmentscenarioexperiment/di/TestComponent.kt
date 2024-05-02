package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidInjectionModule::class])
interface TestComponent {
    fun inject(fragment: ExampleFragment)
}