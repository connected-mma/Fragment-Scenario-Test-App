package com.example.fragmentscenarioexperiment

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExampleFragmentTest : BaseTest() {

    @get:Rule
    val rule = ActivityScenarioRule(TestActivity::class.java)

    @Before
    fun setup() {
        rule.scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testExampleFragment() {
        val fragment = ExampleFragment()

        rule.scenario.onActivity { activity ->
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }

        assertNotNull(fragment.delegate)
        assertEquals("welcome", fragment.delegate.retrieveTitle())
        onView(withId(R.id.title)).check(matches(withText("welcome")))
    }
}