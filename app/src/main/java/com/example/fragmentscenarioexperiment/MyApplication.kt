package com.example.fragmentscenarioexperiment

import android.app.Application
import com.example.fragmentscenarioexperiment.di.DaggerApplicationComponent
import com.example.fragmentscenarioexperiment.utils.Navigator
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder().build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}