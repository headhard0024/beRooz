package no3ratii.mohammad.dev.app.notebook.base.helper

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi

class PublicHelper {

    companion object {
        fun hideKeybord(activity: Activity) {
            activity?.window?.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
            )
        }

        fun circuleReveal(it: View, startcolor: Int, defaultColor: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                CirculeRevealHelper(it, startcolor = startcolor, defaultColor = defaultColor)
                    .init()
            }
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun circuleRevealHelper(it: View,startcolor: Int,defaultColor: Int): CirculeRevealHelper {
            return CirculeRevealHelper(it, startcolor = startcolor, defaultColor = defaultColor)
        }
    }
}