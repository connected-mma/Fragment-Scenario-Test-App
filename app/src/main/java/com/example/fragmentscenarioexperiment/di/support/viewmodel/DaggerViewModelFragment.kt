package com.example.fragmentscenarioexperiment.di.support.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.fragmentscenarioexperiment.di.support.DaggerViewHostFragment
import com.example.fragmentscenarioexperiment.di.support.viewmodel.core.BoundViewModel
import com.example.fragmentscenarioexperiment.di.support.viewmodel.core.PelotonSavedStateViewModelFactory
import dagger.Lazy
import javax.inject.Inject

/**
 * A subclass of [DaggerViewHostFragment] which uses the [PelotonSavedStateViewModelFactory]
 *
 * Using this class means [BoundViewModel] is no longer required. Instead of creating a dagger module for the fragment
 * and writing a BoundViewModel provider function, you can just add a binding for the ViewModel in the app's
 * ViewModelBindingsModule, and get a reference to the viewModel in the FragmentDelegate using `by viewModels()`
 */
abstract class DaggerViewModelFragment : DaggerViewHostFragment() {

    @Inject
    lateinit var defaultViewModelFactory: Lazy<PelotonSavedStateViewModelFactory>

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = defaultViewModelFactory.get().create(this, arguments)
}