package com.example.fragmentscenarioexperiment.di.scopes

/**
 * Marker annotation.
 * Designed to emphasize that instance of the class annotated with current annotation is injected according to the
 * default Dagger's rule - "New instance per every injection"
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Unscoped