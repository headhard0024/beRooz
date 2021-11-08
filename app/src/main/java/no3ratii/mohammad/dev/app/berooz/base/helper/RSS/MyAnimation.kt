package no3ratii.mohammad.dev.app.berooz.base.helper.RSS

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils

/**
 * set animation for news show details activity in scroll time
 */
class MyAnimation(var slideInTop: Int, var view: View?, var context: Context) {
    init {
        val scaleAnim: Animation =
            AnimationUtils.loadAnimation(context, slideInTop)
        view?.startAnimation(scaleAnim)
    }
}