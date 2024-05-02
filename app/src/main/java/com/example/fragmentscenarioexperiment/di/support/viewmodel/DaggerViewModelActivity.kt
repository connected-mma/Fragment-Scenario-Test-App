package com.example.fragmentscenarioexperiment.di.support.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.example.fragmentscenarioexperiment.di.support.DaggerAppCompatActivity
import com.onepeloton.dagger.viewmodel.PelotonSavedStateViewModelFactory
import com.peloton.dagger.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class DaggerViewModelActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var abstractViewModelFactory: PelotonSavedStateViewModelFactory

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = abstractViewModelFactory.create(this)
}