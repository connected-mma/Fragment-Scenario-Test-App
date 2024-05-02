package com.example.fragmentscenarioexperiment.di.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.fragmentscenarioexperiment.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class DaggerModalActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    var fragmentInjector: DispatchingAndroidInjector<Any>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        // pre onCreate setup
        performInjection()
        setTheme(themeId)
        super.onCreate(savedInstanceState)
        setContentView(modalContentView)
        val closeClickListener =
            View.OnClickListener { ignored: View? -> finish() }
        val contentClickListener =
            View.OnClickListener { ignored: View? -> hideKeyboard() }
        val background = findViewById<View>(R.id.modal_background)
        val content = findViewById<FrameLayout>(R.id.modal_content)
        background?.setOnClickListener(closeClickListener)
        content?.setOnClickListener(contentClickListener)

        // inflate the modal layout into the content layout
        LayoutInflater.from(this).inflate(layoutId, content, true)

        // hide keyboard when opening activity
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun onPause() {
        super.onPause()
        hideKeyboard()
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        hideSystemUI()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector!!
    }

    @get:LayoutRes
    @get:VisibleForTesting
    val modalContentView: Int
        get() = R.layout.modal_activity

    private fun performInjection() {
        if (application is HasAndroidInjector) {
            AndroidInjection.inject(this)
        }
    }

    fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null && currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window,  /* decorFitsSystemWindows= */false)
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    @get:StyleRes
    protected abstract val themeId: Int

    @get:LayoutRes
    protected abstract val layoutId: Int
}