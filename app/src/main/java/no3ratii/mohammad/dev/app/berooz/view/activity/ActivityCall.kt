package no3ratii.mohammad.dev.app.berooz.view.activity

import SnackbarHelper
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_call.*
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.berooz.base.G
import no3ratii.mohammad.dev.app.notebook.base.helper.CirculeRevealHelper

class ActivityCall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        initialize()

    }

    private fun initialize() {
        onClickLogic()
    }

    private fun onClickLogic() {
        imgBack.setOnClickListener {
            setCirculeReveal(it)
            finish()
        }

        layMyket.setOnClickListener {
            setCirculeRevealObjects(it)
            val url = "myket://details?id=no3ratii.mohammad.dev.app.beRooz"
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        layWhatsApp.setOnClickListener {
            setCirculeRevealObjects(it)
            startIntent("https://api.whatsapp.com/send?phone=+989900989858" ,"com.whatsapp", "پیام رسان واتساپ روی دستگاه شما موجود نیست",it)
        }

        layLinkedin.setOnClickListener {
            setCirculeRevealObjects(it)
            try {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/mohammad-no3ratii-5a618518a")))
            } catch (e: Exception) {
                object : SnackbarHelper("پیام رسان لینکدین روی دستگاه شما موجود نیست","",it){
                    override fun onClick(it: View) {}
                }.initNoneClickable()
            }
        }

        layCall.setOnClickListener {
            setCirculeRevealObjects(it)
            try {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:09900989858")
                startActivity(intent)
            } catch (e: Exception) {
                object : SnackbarHelper("عدم دسترسی","",it){
                    override fun onClick(it: View) {}
                }.initNoneClickable()
            }
        }
        layWeb.setOnClickListener {
            setCirculeRevealObjects(it)
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mohammadno3ratii.ir"))
                startActivity(browserIntent)
            } catch (e: Exception) {
                object : SnackbarHelper("مرورگر در دسترس نیست","",it){
                    override fun onClick(it: View) {}
                }.initNoneClickable()
            }
        }

        layEmail.setOnClickListener {
            setCirculeRevealObjects(it)
            try {
                val email = Intent(Intent.ACTION_VIEW)
                email.setType("message/rfc822")
                    .setData(Uri.parse("mailto:mohammadno3ratii@gmail.com"))
                    .putExtra(Intent.EXTRA_EMAIL, "mohammadno3ratii@gmail.com")
                    .putExtra(Intent.EXTRA_SUBJECT, "ارسال پیام از طریق ایمیل")
                    .setPackage("com.google.android.gm")
                startActivity(email)
            } catch (e: Exception) {
                object : SnackbarHelper("سرویس ایمیل در دسترس نیست","",it){
                    override fun onClick(it: View) {}
                }.initNoneClickable()
            }
        }

    }

    private fun setCirculeReveal(it: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CirculeRevealHelper(it, startcolor = R.color.CC, defaultColor = R.color.background)
                .init()
        }
    }

    private fun setCirculeRevealObjects(it: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CirculeRevealHelper(it, startcolor = R.color.background, defaultColor = R.color.white)
                .init()
        }
    }

    private fun startIntent(url:String, packages:String, toastText : String , view: View){
        try {
            val social = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            social.setPackage(packages)
            social.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            G.context.startActivity(social)
        } catch (e: Exception) {
            object : SnackbarHelper(toastText,"",view){
                override fun onClick(it: View) {}
            }.initNoneClickable()
        }
    }
}