package com.example.fragmentscenarioexperiment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.fragmentscenarioexperiment.fragments.ExampleFragment
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleFragmentTest : BaseTest(){
    @Test
    fun testExampleFragment() {
        // Use the custom FragmentFactory
        val scenario: FragmentScenario<ExampleFragment> = launchFragmentInContainer(
            factory = TestFragmentFactory()
        )

        // Use onFragment to interact with the fragment
        scenario.onFragment { fragment ->
            // Perform assertions
            assertNotNull(fragment.delegate)
            assertEquals("welcome", fragment.delegate.retrieveTitle())
        }
    }
}

class TestFragmentFactory : FragmentFactory() {
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ExampleFragment::class.java.name -> {
                val fragment = ExampleFragment()
                fragment
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}