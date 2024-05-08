package com.example.fragmentscenarioexperiment

import android.os.Bundle
import android.util.Log

/**
 * Blank activity to run our tests in
 */
class TestActivity : PelotonActivity() {

    init {
        Log.i("@@@", "Launching ${TestActivity::class.simpleName}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }
}