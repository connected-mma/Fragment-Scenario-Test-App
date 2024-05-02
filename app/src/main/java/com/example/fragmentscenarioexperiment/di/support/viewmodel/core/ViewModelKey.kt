package com.example.fragmentscenarioexperiment.di.support.viewmodel.core

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * A dagger [MapKey] used to populate the map of model class -> view model provider.
 * This is used in our [ViewModelFactory] to enable view model dagger2 injection.
 */
@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(
    val value: KClass<out ViewModel>
)