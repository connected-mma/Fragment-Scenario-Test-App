package com.example.fragmentscenarioexperiment.di.support

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity

/**
 * Extension functions / properties for use with using Dagger to inject into Views
 */

/**
 * Building off of dagger-android, this method allows for injection into Android Views.
 * It builds off of what's in [dagger.android.AndroidInjection], but specifically for [View]s.
 *
 * This function will check the [View]s parent to determine if it's a [Fragment] or an [Activity], and use
 * that components [dagger.android.AndroidInjector] to provide the necessary dependencies to the [View].
 *
 * If neither the [Fragment] or the [Activity] has a [dagger.android.AndroidInjector], then the
 * Application's injector will be used.
 *
 * If no [dagger.android.AndroidInjector] implementation can be found, throws an [IllegalArgumentException]
 *
 */
@Throws(IllegalArgumentException::class)
fun View.inject() {
    val hasAndroidInjector = (this.parentFragment as? HasAndroidInjector)
        ?: (this.parentActivity as? HasAndroidInjector)
        ?: (this.context.applicationContext as? HasAndroidInjector)
            .let {
                requireNotNull(it) {
                    "No injector was found for ${this::class.java.canonicalName}"
                }
            }

    val injector =
        requireNotNull(hasAndroidInjector.androidInjector()) {
            "${hasAndroidInjector::class.java.canonicalName}.androidInjector() returned null"
        }

    try {
        injector.inject(this)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException(
            """IllegalArgumentException
            from ${hasAndroidInjector::class.java.canonicalName}.androidInjector().
            Check to make sure you've defined a @ContributesAndroidInjector for ${this::class.java.simpleName}
            in the appropriate Dagger module. Also make sure that that if ${hasAndroidInjector::class.simpleName} is either a Fragment / Activity,
            it extends either ${DaggerViewHostFragment::class.java.simpleName} or ${DaggerAppCompatActivity::class.java.simpleName}
            """.trimMargin(),
            e,
        )
    }
}

fun Any.inject(hasAndroidInjector: HasAndroidInjector) {
    val injector =
        requireNotNull(hasAndroidInjector.androidInjector()) {
            "${hasAndroidInjector::class.java.canonicalName}.androidInjector() returned null"
        }

    try {
        injector.inject(this)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException(
            """IllegalArgumentException
            from ${hasAndroidInjector::class.java.canonicalName}.androidInjector().
            Check to make sure you've defined a @ContributesAndroidInjector for ${this::class.java.simpleName}
            in the appropriate Dagger module. Also make sure that that if ${hasAndroidInjector::class.simpleName} is either a Fragment / Activity,
            it extends either ${DaggerViewHostFragment::class.java.simpleName} or ${DaggerAppCompatActivity::class.java.simpleName}
            """.trimMargin(),
            e,
        )
    }
}

/**
 * Finds the parent [Fragment] of this [View], or null if the [View] is not housed in a [Fragment]
 */
val View.parentFragment: Fragment?
    get() = context.fragmentContextWrapper?.fragment?.get()
        ?: try {
            FragmentManager.findFragment<Fragment>(this)
        } catch (e: IllegalStateException) {
            null
        }

/**
 * Finds the [FragmentContextWrapper] from the [Context] instance if it is available.
 *
 * If the context itself is a [FragmentContextWrapper] it is returned.
 * If the context is a [ContextWrapper] the [FragmentContextWrapper] from the base context.
 * Otherwise, null is returned.
 */
val Context.fragmentContextWrapper: FragmentContextWrapper?
    get() =
        when {
            this is FragmentContextWrapper -> this
            this is ContextWrapper && baseContext != null -> baseContext.fragmentContextWrapper
            else -> null
        }

/**
 * Finds the parent [Activity] of this [View], or null if the [View] is not housed in a [Fragment]
 */
val View.parentActivity: Activity?
    get() {
        var context = this.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }