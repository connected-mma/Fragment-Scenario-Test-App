package com.example.fragmentscenarioexperiment.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.fragmentscenarioexperiment.R
import com.example.fragmentscenarioexperiment.di.support.DaggerViewHostFragment
import com.example.fragmentscenarioexperiment.di.support.viewmodel.DaggerViewModelFragment
import com.example.fragmentscenarioexperiment.utils.AnalyticsHandler
import com.example.fragmentscenarioexperiment.utils.ExampleFragmentDelegate
import com.example.fragmentscenarioexperiment.utils.data.Event
import javax.inject.Inject

class  ExampleFragment : DaggerViewHostFragment() {

    @Inject lateinit var delegate: ExampleFragmentDelegate

    @Inject lateinit var analyticsHandler: AnalyticsHandler

    private lateinit var titleText: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_example, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleText = view.findViewById(R.id.title)
        val title = delegate.retrieveTitle()

        titleText.text = title
        Log.i("@@@", "title: $title")

        delegate.redirectUser()

        analyticsHandler.emitEvent(Event("onViewCreated", "0"))
    }
}