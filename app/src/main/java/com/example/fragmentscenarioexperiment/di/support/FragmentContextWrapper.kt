package com.example.fragmentscenarioexperiment.di.support

import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference

class FragmentContextWrapper(
    private val baseInflater: LayoutInflater,
    val fragment: WeakReference<Fragment>,
) : ContextWrapper(baseInflater.context) {

    private var inflater: LayoutInflater? = null

    override fun getSystemService(name: String): Any? {
        if (Context.LAYOUT_INFLATER_SERVICE != name) {
            return baseContext.getSystemService(name)
        }

        if (inflater == null) {
            inflater = baseInflater.cloneInContext(this)
        }

        return inflater!!
    }
}

fun LayoutInflater.wrappedWithFragment(fragment: Fragment): LayoutInflater =
    LayoutInflater.from(FragmentContextWrapper(this, WeakReference(fragment)))