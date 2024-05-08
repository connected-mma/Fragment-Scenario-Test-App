package com.example.fragmentscenarioexperiment

import android.os.Bundle
import android.util.Log
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment

class MainActivity : PelotonActivity() {

    init {
        Log.i("@@@", "Launching ${MainActivity::class.simpleName}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ExampleFragment())
                .commitNow()
        }
    }
}