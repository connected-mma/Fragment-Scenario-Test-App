package com.example.fragmentscenarioexperiment

import com.example.fragmentscenarioexperiment.di.DaggerApplicationComponent
import com.example.fragmentscenarioexperiment.utils.Navigator
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class MyApplication : DaggerApplication() {

    @Inject
    lateinit var navigator: Navigator

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(this).also {
            it.inject(this)
        }
    }
}