package com.example.fragmentscenarioexperiment.di.support

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

typealias PelotonDaggerViewHostFragment = DaggerViewHostSupportFragment<Unit>

abstract class DaggerViewHostSupportFragment<T> : DaggerSupportFragment<T>(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    /**
     * Wraps context in [FragmentContextWrapper] so Views inflated by this [Fragment] can
     * get a reference to the [Fragment] for Dagger injection
     *
     */
    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater =
        super.onGetLayoutInflater(savedInstanceState).wrappedWithFragment(this)

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}