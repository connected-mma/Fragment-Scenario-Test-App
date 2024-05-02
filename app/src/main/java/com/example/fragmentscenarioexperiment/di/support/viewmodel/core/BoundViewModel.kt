package com.example.fragmentscenarioexperiment.di.support.viewmodel.core

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import javax.inject.Provider

/**
 * Please consider using the `by viewModels()` extension function on [FragmentDelegate] instead. To use this you will
 * need to:
 *
 * 1. Ensure the Fragment extends [DaggerViewModelFragment], [DaggerViewModelDialogFragment], or [ModalFragment]
 * 2. Ensure the Delegate extends [FragmentDelegate] or [PerformanceFragmentDelegate]
 * 3. Ensure a binding for the ViewModel is added in the app's [ViewModelBindingsModule]
 * 4. Delete the `@Provides providesBoundViewModel()` method in the dagger module. If the dagger module is now empty,
 * it can be deleted.
 * 5. Delete the `boundViewModel: BoundViewModel<VM>` constructor argument from the delegate and replace the
 * `private val viewModel = boundViewModel.get()` with `private val viewModel: MyViewModel by viewModels()`
 *
 * ============================
 *
 * Defines a Dagger [Provider] for [ViewModel]s that are bound to a specific [ViewModelStoreOwner].
 *
 * This differentiates from the raw Dagger [Provider] for the [ViewModel] that performs dependency
 * injection, but does not link the [ViewModel] to the [ViewModelStoreOwner].
 *
 * The [BoundViewModel] [Provider] is used to retrieve either a fresh instance of the [ViewModel] or
 * one that is already bound to a specific [ViewModelStoreOwner]'s [ViewModelStore].
 *
 * This is an alternative to providing mapping for [ViewModel]s in Dagger via [ViewModelKey]
 * and then using our [ViewModelFactory]. That approach is good when many view models are exposed
 * in a Dagger module.
 *
 * Typical usage is as follows:
 *
 * ViewModel -
 *   ```
 *   @Unscoped
 *   class MyComponentViewModel @Inject constructor(
 *     ...
 *   ) : ViewModel()
 *   ```
 *
 * Dagger wiring to provide a BoundViewModel<MyComponentViewModel> -
 *   ```
 *   @Provides
 *   internal fun providesMyComponentViewModel(
 *     ...
 *     provider: Provider<MyComponentViewModel>
 *   ): BoundViewModel<MyComponentViewModel> = provider.toBoundViewModel(...)
 *   ```
 *
 * Injection of the view model (e.g. in delegate) -
 *   ```
 *   boundViewModel: BoundViewModel<MyComponentViewModel>,
 *   ```
 *
 * Local val to get the actual view model in your component (e.g. delegate) -
 *   ```
 *   private val viewModel = boundViewModel.get()
 *   ```
 *
 * @see [ViewModelWithParameter] for information on how to use [toBoundViewModel] to provide a
 * view component parameter during creation.
 */
fun interface BoundViewModel<TViewModel : ViewModel> : Provider<TViewModel>

/**
 * Interface for [ViewModel] subclasses that need to be notified of lifecycle events.
 */
interface ViewModelLifecycle {
    /**
     * This method is invoked when the [ViewModel] is instantiated. Perform work here that would originally be done in
     * the `init` block.
     */
    fun onCreated()
}

/**
 * Please consider using the `by viewModels()` extension function on [FragmentDelegate] instead. To use this you will
 * need to:
 *
 * 1. Ensure the Fragment extends [DaggerViewModelFragment], [DaggerViewModelDialogFragment], or [ModalFragment]
 * 2. Ensure the Delegate extends [FragmentDelegate] or [PerformanceFragmentDelegate]
 * 3. Ensure a binding for the ViewModel is added in the app's [ViewModelBindingsModule]
 * 4. Delete the `@Provides providesBoundViewModel()` method in the dagger module. If the dagger module is now empty,
 * it can be deleted.
 * 5. Delete the `boundViewModel: BoundViewModel<VM>` constructor argument from the delegate and replace the
 * `private val viewModel = boundViewModel.get()` with `private val viewModel: MyViewModel by viewModels()`
 * 6. Consider using `FragmentDelegate::setViewModel` extension function in `:test-support:android` if you have
 *  delegate tests accessing the viewmodel
 *
 *
 * ============================
 *
 * Converts a Dagger [Provider] for a [ViewModel] to one that can produce [BoundViewModel]
 * instances, bound to a specific [ViewModelStoreOwner].
 *
 * Optionally takes a [Bundle] instance. If the view model to be bound is of the type
 * [ViewModelWithParameter], the bundle is passed to the [ViewModelWithParameter.loadParameter]
 * function so that the [ViewModel]'s parameter is available before [ViewModelLifecycle] is
 * processed.
 *
 * If the [ViewModel] implements [ViewModelLifecycle] it will be notified when it is created via
 * [ViewModelLifecycle.onCreated], after parameter processing is completed.
 */
inline fun <reified TViewModel : ViewModel> Provider<out TViewModel>.toBoundViewModel(
    storeOwner: ViewModelStoreOwner,
    parameterBundle: Bundle? = null
): BoundViewModel<TViewModel> {
    val singleViewModelFactory = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (!TViewModel::class.java.isAssignableFrom(modelClass)) {
                error("Cannot process request for $modelClass, this factory only supports ${TViewModel::class}")
            }

            return (get() as T).apply {
                // Trigger the parameter loading from the given bundle if this is a view model
                // with parameter.
                (this as? ViewModelWithParameter<*>)?.loadParameter(parameterBundle)

                // Then process the view model lifecycle callbacks if they were defined.
                (this as? ViewModelLifecycle)?.onCreated()
            }
        }
    }

    val provider = ViewModelProvider(storeOwner, singleViewModelFactory)

    return object : BoundViewModel<TViewModel> {
        override fun get(): TViewModel =
            provider.get(TViewModel::class.java)
    }
}