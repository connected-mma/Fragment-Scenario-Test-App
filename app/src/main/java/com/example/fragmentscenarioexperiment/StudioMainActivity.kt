package com.example.fragmentscenarioexperiment

import android.os.Bundle
import android.util.Log
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import dagger.android.support.DaggerAppCompatActivity

class StudioMainActivity : DaggerAppCompatActivity() {

    init {
        Log.i("@@@","Launching ${StudioMainActivity::class.simpleName}")
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