package com.example.fragmentscenarioexperiment

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class ActivityScenarioTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun testExampleFragment() {
        // Use the custom FragmentFactory

        val activityScenario: ActivityScenario<MainActivity> = ActivityScenario.launch(MainActivity::class.java)

        // Use onFragment to interact with the fragment
        activityScenario.onActivity { activity ->
            val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
                ExampleFragment::class.java.classLoader,
                ExampleFragment::class.java.name
            ) as ExampleFragment

            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, ExampleFragment())
                .commitNow()

            fragment.onAttach(activity.baseContext)

            assertNotNull(fragment.delegate)
            assertEquals("welcome", fragment.delegate.retrieveTitle())
        }
    }
}