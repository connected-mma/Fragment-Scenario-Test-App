package com.example.fragmentscenarioexperiment.utils.data

data class Event(
    val title: String,
    val id: String,
    val properties: Map<String, String>? = null
)