package com.example.fragmentscenarioexperiment.di.support.viewmodel.core

import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.multibindings.Multibinds

@Module
@Suppress("UNUSED")
abstract class ViewModelModule {

    @Multibinds
    abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>

    @Multibinds
    abstract fun assistedViewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards AssistedSavedStateViewModelFactory<out ViewModel>>
}