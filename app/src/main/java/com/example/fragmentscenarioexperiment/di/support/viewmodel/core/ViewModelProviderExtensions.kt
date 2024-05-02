package com.example.fragmentscenarioexperiment.di.support.viewmodel.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Syntactic sugar for accessing shared view models from a view model in a similar way to the
 * 'by activityViewModels()` syntax that can be used in fragments.
 */
inline fun <reified VM : ViewModel> ViewModelProvider.viewModels() = lazy {
    get(VM::class.java)
}