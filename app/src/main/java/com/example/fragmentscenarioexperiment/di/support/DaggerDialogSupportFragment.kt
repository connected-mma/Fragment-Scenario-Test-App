package com.example.fragmentscenarioexperiment.di.support

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.DialogFragment
import dagger.android.support.AndroidSupportInjection

open class DaggerDialogSupportFragment<T> : DialogFragment() {

    var dataParam: T? = null

    protected open fun initDaggerDataParam(bundle: Bundle?): T? = null

    override fun onAttach(context: Context) {
        dataParam = initDaggerDataParam(arguments)
        if (context.applicationContext is RealApplicationAndroidInjector) {
            AndroidSupportInjection.inject(this)
        }
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnKeyListener { _: DialogInterface?, keyCode: Int, event: KeyEvent ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                return@setOnKeyListener onKeyDown(keyCode, event)
            }
            false
        }
        return dialog
    }

    protected open fun onKeyDown(
        keyCode: Int,
        event: KeyEvent
    ): Boolean {
        return false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (dialog != null && dialog!!.window != null) {
            val window = dialog!!.window!!
            window.callback = DialogFragmentUserInteractionCallback(
                window.callback,
                requireActivity()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        dialog?.window?.let {
            WindowCompat.setDecorFitsSystemWindows(it, false)
            val windowInsetsController = WindowCompat.getInsetsController(it, it.decorView)
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
            windowInsetsController.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}