package com.example.fragmentscenarioexperiment.di.support.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.fragmentscenarioexperiment.di.support.DaggerAppCompatActivity
import com.example.fragmentscenarioexperiment.di.support.viewmodel.core.PelotonSavedStateViewModelFactory
import javax.inject.Inject

abstract class DaggerViewModelActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var abstractViewModelFactory: PelotonSavedStateViewModelFactory

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = abstractViewModelFactory.create(this)
}