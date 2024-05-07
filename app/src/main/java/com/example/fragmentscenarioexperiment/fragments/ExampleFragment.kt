package com.example.fragmentscenarioexperiment.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.fragmentscenarioexperiment.FragmentScopedClass
import com.example.fragmentscenarioexperiment.R
import com.example.fragmentscenarioexperiment.utils.ExampleFragmentDelegate
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ExampleFragment : DaggerFragment(R.layout.fragment_example) {

    @Inject lateinit var delegate: ExampleFragmentDelegate
    @Inject lateinit var testClass: FragmentScopedClass

    private lateinit var titleText: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleText = view.findViewById(R.id.title)
        val title = delegate.retrieveTitle()

        titleText.text = title
        Log.i("@@@", "title: $title")
        Log.i("@@@", "test class $testClass")

        delegate.redirectUser()
    }
}