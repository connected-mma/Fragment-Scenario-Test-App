package com.example.fragmentscenarioexperiment.di

import androidx.test.core.app.ApplicationProvider
import com.example.fragmentscenarioexperiment.BaseTest
import com.example.fragmentscenarioexperiment.di.scopes.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@ApplicationScope
@Component(modules = [AndroidInjectionModule::class, ActivityModule::class,
    FragmentModule::class])
interface TestComponent: AndroidInjector<TestApplication> {

    fun inject(baseTest: BaseTest)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: TestApplication): Builder
        fun build(): TestComponent
    }

}

fun testAppComponent(): TestComponent = ApplicationProvider.getApplicationContext<TestApplication>().component