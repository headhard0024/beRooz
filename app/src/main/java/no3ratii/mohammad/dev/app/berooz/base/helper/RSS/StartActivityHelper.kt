package no3ratii.mohammad.dev.app.berooz.base.helper.RSS

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.notebook.base.helper.CirculeRevealHelper

data class StartActivityHelper(var context: Context, var activity: Activity) {

    private val intent = Intent(context, activity::class.java)
    private var isFinish = false
    private var newFlag = false


    fun finish(): StartActivityHelper {
        isFinish = true
        return this
    }

    fun setReveal(view: View , startColor : Int , defaultColor : Int): StartActivityHelper {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CirculeRevealHelper(view, startColor, defaultColor)
                .radius(10)
                .init()
        }
        return this
    }

    fun nowFlag():StartActivityHelper {
        newFlag = true
        return this
    }

    fun init() {
        if (newFlag) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        context.startActivity(intent)

        if (isFinish) {
            activity?.finish()
        }
    }


}