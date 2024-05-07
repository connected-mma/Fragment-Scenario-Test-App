package com.example.fragmentscenarioexperiment

import com.example.fragmentscenarioexperiment.di.DaggerTestComponent
import com.example.fragmentscenarioexperiment.di.testAppComponent

open class BaseTest {
    init {
        testAppComponent().inject(this)
    }
}