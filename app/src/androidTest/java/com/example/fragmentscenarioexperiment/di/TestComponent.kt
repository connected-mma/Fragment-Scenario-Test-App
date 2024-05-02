package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.di.scopes.ApplicationScope
import com.example.fragmentscenarioexperiment.di.scopes.FragmentScope
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@FragmentScope
@Component(modules = [AndroidInjectionModule::class])
interface TestComponent {
    fun inject(fragment: ExampleFragment)
}