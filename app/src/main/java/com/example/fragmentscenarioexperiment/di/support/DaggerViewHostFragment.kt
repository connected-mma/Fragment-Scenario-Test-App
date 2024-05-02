package com.example.fragmentscenarioexperiment.di.support

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import kotlin.system.measureTimeMillis

abstract class DaggerViewHostFragment : Fragment(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    /**
     * Wraps context in [FragmentContextWrapper] so Views inflated by this [Fragment] can
     * get a reference to the [Fragment] for Dagger injection
     *
     */
    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater =
        super.onGetLayoutInflater(savedInstanceState).wrappedWithFragment(this)

    override fun onAttach(context: Context) {
        val injectionDuration = measureTimeMillis {
            AndroidSupportInjection.inject(this)
        }

        maybeReportInjection(injectionDuration)

        super.onAttach(context)
    }

    // No-op. Must be a PerformanceMonitorFragment to actually report.
    open fun maybeReportInjection(injectionDuration: Long) = Unit

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}