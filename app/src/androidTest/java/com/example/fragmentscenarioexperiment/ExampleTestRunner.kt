package com.example.fragmentscenarioexperiment

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.example.fragmentscenarioexperiment.test.TestApplication

class ExampleTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApplication::class.java.name, context)
    }
}