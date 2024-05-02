package com.example.fragmentscenarioexperiment.di.support

import android.content.Context
import androidx.preference.PreferenceFragmentCompat
import dagger.android.support.AndroidSupportInjection

abstract class DaggerPreferenceFragmentx : PreferenceFragmentCompat() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}