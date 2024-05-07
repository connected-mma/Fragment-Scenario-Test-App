package com.example.fragmentscenarioexperiment

import androidx.fragment.app.Fragment
import com.example.fragmentscenarioexperiment.di.scopes.FragmentScope
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ExampleFragmentModule {

    companion object {
        @Provides
        @FragmentScope
        fun provideSomething(): FragmentScopedClass {
            return FragmentScopedClass()
        }
    }

    @Binds
    fun provideFragment(fragment: ExampleFragment): Fragment
}