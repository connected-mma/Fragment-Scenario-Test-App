package com.example.fragmentscenarioexperiment.utils

import com.example.fragmentscenarioexperiment.utils.data.Event

interface AnalyticsHandler {
    fun emitEvent(event: Event)
}