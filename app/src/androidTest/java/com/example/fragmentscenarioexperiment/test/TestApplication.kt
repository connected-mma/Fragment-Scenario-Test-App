package com.example.fragmentscenarioexperiment.test

import android.app.Application
import android.util.Log
import com.example.fragmentscenarioexperiment.di.DaggerTestComponent
import com.example.fragmentscenarioexperiment.di.TestComponent
import com.example.fragmentscenarioexperiment.utils.AnalyticsHandler
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TestApplication : Application(), HasAndroidInjector {

    lateinit var component: TestComponent

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    init {
        Log.i("TYLTYL", "TYLTYL TestApplication init()")
    }

    override fun onCreate() {
        Log.i("TYLTYL", "TYLTYL TestApplication onCreate()")
        super.onCreate()
    }

    fun performInjection(
        analyticsHandler: AnalyticsHandler
    ) {
        component = DaggerTestComponent.builder()
            .application(this)
            .analyticsHandler(analyticsHandler)
            .build()
            .also { testComponent ->
                Log.i("TYLTYL", "TYLTYL TestApplication inject()")
            }
        component.inject(this)
    }
}