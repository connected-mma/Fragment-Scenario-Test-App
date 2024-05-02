package com.example.fragmentscenarioexperiment.di.support

import android.content.Context
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

typealias PelotonDaggerSupportFragment = DaggerSupportFragment<Unit>

abstract class DaggerSupportFragment<T> : Fragment() {

    var dataParam: T? = null

    protected open fun initDaggerDataParam(bundle: Bundle?): T? = null

    @CallSuper
    override fun onAttach(context: Context) {
        dataParam = initDaggerDataParam(arguments)
        if (context.applicationContext is RealApplicationAndroidInjector) {
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(context)
    }
}