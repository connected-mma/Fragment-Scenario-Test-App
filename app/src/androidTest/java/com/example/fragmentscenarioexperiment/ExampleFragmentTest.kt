package com.example.fragmentscenarioexperiment

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import com.example.fragmentscenarioexperiment.test.TestApplication
import com.example.fragmentscenarioexperiment.utils.AnalyticsHandler
import com.example.fragmentscenarioexperiment.utils.data.Event
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ExampleFragmentTest {

    init {
        println("TYLTYL ExampleFragmentTest init")
    }

    private val analyticsHandler = mock<AnalyticsHandler>()

    lateinit var scenario: ActivityScenario<TestActivity>

    private fun deferredInjection() {
        MockitoAnnotations.initMocks(this)
        ApplicationProvider.getApplicationContext<TestApplication>().performInjection(
            analyticsHandler = analyticsHandler
        )
        scenario = ActivityScenario.launch(TestActivity::class.java)
    }

    @Before
    fun setup() {
        println("TYLTYL ExampleFragmentTest setup()")
        deferredInjection()
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun testExampleFragment() {
        println("TYLTYL ExampleFragmentTest testExampleFragment()")
        val fragment = ExampleFragment()

        scenario.onActivity { activity ->
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }

        assertNotNull(fragment.delegate)
        assertEquals("welcome", fragment.delegate.retrieveTitle())
        onView(withId(R.id.title)).check(matches(withText("welcome")))

        verify(analyticsHandler).emitEvent(Event("onViewCreated", "0"))
    }
}