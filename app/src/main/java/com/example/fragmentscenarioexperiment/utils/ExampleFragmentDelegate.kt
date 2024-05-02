package com.example.fragmentscenarioexperiment.utils

import android.util.Log
import com.example.fragmentscenarioexperiment.di.scopes.FragmentScope
import javax.inject.Inject

@FragmentScope
class ExampleFragmentDelegate @Inject constructor(
    private val navigator: Navigator
) {
    init {
        Log.d("@@@", "ExampleFragmentDelegate init")
    }

    fun retrieveTitle() = "welcome"

    fun redirectUser() = navigator.navigateTo(Destination.PROFILE)
}