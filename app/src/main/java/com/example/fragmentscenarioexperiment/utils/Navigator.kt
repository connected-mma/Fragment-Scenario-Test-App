package com.example.fragmentscenarioexperiment.utils

import android.util.Log
import com.example.fragmentscenarioexperiment.di.scopes.ApplicationScope
import javax.inject.Inject

enum class Destination {
    HOME, PROFILE, SETTINGS
}

@ApplicationScope
class Navigator @Inject constructor() {
    fun navigateTo(destination: Destination) {
        Log.i("@@@", "Navigating to $destination")
    }
}