package com.example.fragmentscenarioexperiment.di.support

import android.app.Activity
import android.view.MotionEvent
import android.view.Window

class DialogFragmentUserInteractionCallback(
    private val originalCallback: Window.Callback,
    private val activity: Activity?
) : Window.Callback by originalCallback {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            activity?.onUserInteraction()
        }
        return originalCallback.dispatchTouchEvent(event)
    }
}