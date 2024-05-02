package com.example.fragmentscenarioexperiment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
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