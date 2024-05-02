package com.example.fragmentscenarioexperiment.utils

import android.util.Log
import javax.inject.Inject

enum class Destination {
    HOME, PROFILE, SETTINGS
}

class Navigator @Inject constructor() {
    fun navigateTo(destination: Destination) {
        Log.i("@@@", "Navigating to $destination")
    }
}