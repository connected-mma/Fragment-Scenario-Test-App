package com.example.fragmentscenarioexperiment.utils

import android.util.Log
import com.example.fragmentscenarioexperiment.di.scopes.FragmentScope
import com.example.fragmentscenarioexperiment.utils.data.Event
import javax.inject.Inject

@FragmentScope
class ExampleAnalyticsHandler @Inject constructor(): AnalyticsHandler {
    override fun emitEvent(event: Event) {
        Log.d("@@@","AnalyticsHandler: emitting event: $event")
    }
}