package com.example.fragmentscenarioexperiment.di.support

import dagger.android.HasAndroidInjector

/**
 * Interface used in "actual" Application implementations to differentiate between test implementations
 * in which we want `TestApplication is RealApplication` to == `false`
 */
interface RealApplicationAndroidInjector : HasAndroidInjector