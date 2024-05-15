package com.example.fragmentscenarioexperiment.di

import androidx.test.core.app.ApplicationProvider
import com.example.fragmentscenarioexperiment.BaseTest
import com.example.fragmentscenarioexperiment.di.scopes.ApplicationScope
import com.example.fragmentscenarioexperiment.test.TestApplication
import com.example.fragmentscenarioexperiment.utils.AnalyticsHandler
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScope
@Component(
    modules = [
        AndroidInjectionModule::class,
        TestActivityModule::class
    ]
)
interface TestComponent : AndroidInjector<TestApplication> {
    fun inject(baseTest: BaseTest)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TestApplication): Builder

        @BindsInstance
        fun analyticsHandler(analyticsHandler: AnalyticsHandler): Builder

        fun build(): TestComponent
    }
}

fun testAppComponent(): TestComponent = ApplicationProvider.getApplicationContext<TestApplication>().component