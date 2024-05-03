package com.example.fragmentscenarioexperiment.di

import com.example.fragmentscenarioexperiment.di.scopes.ApplicationScope
import com.example.fragmentscenarioexperiment.di.scopes.FragmentScope
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import dagger.Component
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule

@Subcomponent
@FragmentScope
interface FragmentSubcomponent {
    fun inject(fragment: ExampleFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentSubcomponent
    }
}

@ApplicationScope
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class,
    FragmentModule::class])
interface TestComponent {
    fun customFragmentSubcomponent(): FragmentSubcomponent.Factory
}