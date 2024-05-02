package com.example.fragmentscenarioexperiment.di.support.viewmodel.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentscenarioexperiment.di.scopes.Unscoped
import javax.inject.Inject
import javax.inject.Provider

/**
 * A [ViewModelProvider.Factory] implementation that takes a Dagger provided map of model class
 * to provider of the model (via [ViewModelKey]) and can create model instances when requested.
 *
 * NOTE: This is an [Unscoped] version and is unscoped. For specific scopes
 *       you should use dagger to Bind an instance of this factory to the correct scope
 *       within your view model module.
 */
@Unscoped
class ViewModelFactory @Inject constructor(
    private val modelProviders: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = findModelProvider(modelClass)
            ?: error("Unknown model class: $modelClass")

        return provider.get() as T
    }

    /**
     * Finds the provider by class key, either by direct match or subtype matching (slower).
     */
    private fun findModelProvider(modelClass: Class<out ViewModel>): Provider<out ViewModel>? =
        modelProviders[modelClass] ?: modelProviders.entries.find { entry ->
            modelClass.isAssignableFrom(entry.key)
        }?.value
}