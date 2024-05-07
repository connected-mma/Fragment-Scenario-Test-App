package com.example.fragmentscenarioexperiment

import com.example.fragmentscenarioexperiment.di.DaggerApplicationComponent
import com.example.fragmentscenarioexperiment.utils.Navigator
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class MyApplication : DaggerApplication() {

    @Inject
    lateinit var navigator: Navigator

    private var applicationInjector: AndroidInjector<out DaggerApplication>

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return applicationInjector
    }

    init {
        applicationInjector = DaggerApplicationComponent.factory()
            .create(this).also {
                it.inject(this)
            }
    }
}