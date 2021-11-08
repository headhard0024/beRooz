package no3ratii.mohammad.dev.app.berooz.view.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_splash.layRoot
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.berooz.base.G
import kotlin.math.hypot


class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        layRoot.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onLayoutChange(
                view: View,
                i: Int,
                i1: Int,
                i2: Int,
                i3: Int,
                i4: Int,
                i5: Int,
                i6: Int,
                i7: Int
            ) {
                view.removeOnLayoutChangeListener(this)
                //Add circular revel animation on activity start
                view.postDelayed(Runnable {
                    img.visibility = View.VISIBLE
                    init(img, R.color.white, 500)
                }, 0)

                view.postDelayed(Runnable {
                    init(layRoot, R.color.white, 1000)
                }, 500)

                view.postDelayed(Runnable {
                    val intent = Intent(applicationContext, ActivityMain::class.java)
                    startActivity(intent)
                    finish()
                }, 500)
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun init(view: View, startcolor: Int, duration: Long) {
        view.setBackgroundColor(G.resources.getColor(startcolor))
        val endRadius =
            hypot(view.width.toDouble(), view.height.toDouble()).toInt()
        val anim: Animator = ViewAnimationUtils.createCircularReveal(
            view,
            view.measuredWidth / 2,
            view.measuredHeight / 2,
            0F,
            endRadius.toFloat()
        )
        anim.addListener(
            object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)

                }
            })
        if (duration > 10) {
            anim.duration = duration
        }
        anim.start()
    }
}