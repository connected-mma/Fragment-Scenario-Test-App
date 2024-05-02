package com.example.fragmentscenarioexperiment.di.support

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection

open class DaggerBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}

fun FragmentActivity.findBottomSheetFragmentByTagOrNull(tag: String): DaggerBottomSheetDialogFragment? {
    return supportFragmentManager.findFragmentByTag(tag) as? DaggerBottomSheetDialogFragment
}