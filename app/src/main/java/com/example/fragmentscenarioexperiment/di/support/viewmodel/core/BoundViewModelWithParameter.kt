package com.example.fragmentscenarioexperiment.di.support.viewmodel.core

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

/**
 * The default key used to load/store a [ViewModelParameter] in a [Bundle].
 * This usually applies to an activity's extras (via intent) or a [Fragment]'s arguments.
 */
const val DEFAULT_PARAMETER_KEY = "boundViewModelParameter"

/**
 * Defines a type safe parameter that can be passed to a view component (e.g. activity/fragment).
 *
 * Provides for common interface for utilities dealing with such parameters, including:
 *   - storing into an intent or argument bundle ([store])
 *   - loading from intent or argument bundle ([toBoundViewModel] with [Bundle])
 *   - access within a view model ([ViewModelWithParameter.requireParameter])
 */
interface ViewModelParameter : Parcelable {
    /**
     * Stores the instance in the provided bundle using the given key.
     * If no key is provided the [DEFAULT_PARAMETER_KEY] will be used.
     */
    fun store(bundle: Bundle, parameterKey: String = DEFAULT_PARAMETER_KEY) {
        bundle.putParcelable(parameterKey, this)
    }
}

/**
 * Stores the instance to a freshly constructed [Bundle] instead of a provided one.
 * Useful when the [ViewModelParameter] is the only argument being stored to the [Bundle].
 */
fun ViewModelParameter.store(parameterKey: String = DEFAULT_PARAMETER_KEY): Bundle =
    Bundle().also { store(it, parameterKey) }

/**
 * Stores a [ViewModelParameter] into a newly created [Bundle] and sets it as this [Fragment]'s
 * arguments. Useful when the parameter is the only thing in the [Bundle] via usage:
 *
 *   ```
 *   MyFragment().apply {
 *       setParameter(MyFragmentParameter(...))
 *   }
 *   ```
 */
fun Fragment.setParameter(
    parameter: ViewModelParameter,
    parameterKey: String = DEFAULT_PARAMETER_KEY
) {
    arguments = parameter.store(parameterKey)
}

/**
 * Convenience utility for setting a [ViewModelParameter] on a [Fragment] instance and returning
 * the same instance in one shot. Useful for when the only configuration for the newly created
 * [Fragment] instance is the parameter. If you have a case where you are setting the parameter
 * as well as performing other work on the [Fragment], use [setParameter] instead.
 *
 * Example:
 *
 *   ```
 *   MyFragment().applyParameter(MyFragmentParameter(...))
 *   ```
 */
fun <TFragment : Fragment> TFragment.applyParameter(
    parameter: ViewModelParameter,
    parameterKey: String = DEFAULT_PARAMETER_KEY
): TFragment {
    setParameter(parameter, parameterKey)
    return this
}

/**
 * Supertype of [ViewModel]s that want their view component's parameter to be automatically
 * injected during creation via the [toBoundViewModel] utility. Extends jetpack's [ViewModel]
 * to provide all the usual functionality while adding the ability to retrieve the injected
 * [ViewModelParameter] instance via [loadParameter].
 *
 * The [parameter] var will be automatically populated as the [ViewModel] instance is created,
 * but ONLY if it is created via a [BoundViewModel] instance returned from [toBoundViewModel]
 * that has been provided a [Bundle] instance.
 *
 * The full typical usage is as follows:
 *
 * Define parameter class (parcelize is recommended but not required) -
 *   ```
 *   @Parcelize
 *   data class MyParameter(
 *     val fooId: String
 *   ) : ViewModelParameter
 *   ```
 *
 * ViewModel -
 *   ```
 *   @Unscoped
 *   class MyComponentViewModel @Inject constructor(
 *     ...
 *   ) : ViewModelWithParameter<MyParameter>()
 *   ```
 *
 * Dagger wiring to provide a BoundViewModel<MyComponentViewModel> -
 *   ```
 *   @Provides
 *   internal fun providesMyComponentViewModel(
 *     ...
 *     provider: Provider<MyComponentViewModel>
 *   ): BoundViewModel<MyComponentViewModel> = provider.toBoundViewModel(..., bundleInstance)
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
 * Providing the parameter within view component creation (e.g. in fragment) -
 *   ```
 *   companion object {
 *     fun newInstance(id: String): MyFragment {
 *       return MyFragment().apply {
 *         arguments = Bundle().apply {
 *           MyParameter(id).store(this)
 *         }
 *       }
 *     }
 *   }
 *   ```
 *
 * Retrieving the parameter within the view model -
 *   ```
 *   fun doMyWorkMyViewModel() {
 *      requireParameter().fooId
 *   }
 *   ```
 */
abstract class ViewModelWithParameter<TParameter : ViewModelParameter> : ViewModel() {
    /**
     * The currently known [ViewModelParameter] of this [ViewModel]'s view component.
     */
    var parameter: TParameter? = null

    /**
     * The key used to retrieve the [ViewModel]'s argument when [loadParameter] is called.
     */
    protected open val parameterKey = DEFAULT_PARAMETER_KEY

    /**
     * Ensures that there is an injected [ViewModelParameter] for this [ViewModel]'s
     * view component and returns the instance.
     *
     * This is the main way the parameter should be accessed in subclasses of this type.
     */
    fun requireParameter() =
        requireNotNull(parameter)

    /**
     * Loads the parameter value for this view model from the provided [Bundle], if:
     *   - the bundle is not null
     *   - the [parameterKey] mapping exists in the bundle
     */
    fun loadParameter(bundle: Bundle?) {
        if (bundle == null || !bundle.containsKey(parameterKey)) {
            return
        }
        parameter = bundle.getParcelable(parameterKey)
    }
}