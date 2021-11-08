package no3ratii.mohammad.dev.app.berooz.view.activity

import SnackbarHelper
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import kotlinx.android.synthetic.main.activity_show_details.*
import kotlinx.android.synthetic.main.content_scrolling.*
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.MyAnimation


class ActivityShowDetails : AppCompatActivity() {

    private var imageUrl: String? = ""
    private var title: String? = ""
    private var desc: String? = ""
    private var link: String? = ""
    private var showAnim = true

    private fun resiveValues() {
        imageUrl = intent.getStringExtra("URL")
        title = intent.getStringExtra("TITLE")
        desc = intent.getStringExtra("DESC")
        link = intent.getStringExtra("LINK")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        initToolbar()
        resiveValues()
        initialize()
        initScrollViewAnim()
    }

    private fun initialize() {
        //check for not null value
        if (!title.isNullOrEmpty() && !desc.isNullOrEmpty()) {
            txtTitle.text = title
            txtTtitle.text = title
            txtDesc.text = Html.fromHtml(desc)
        } else {
            txtTitle.text = " :| مشکلی در دریافت اطلاعات پیش آمده"
        }

        if (!imageUrl.isNullOrEmpty()) {
            Glide
                .with(this)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(img)
        }
        setOnClicks()
    }

    private fun setOnClicks() {
        imgBack.setOnClickListener {
            finish()
        }

        fabCopy.setOnClickListener {
            saveTitleAndDescInClipboard()
            object : SnackbarHelper("متن کپی شد ...", "", it) {
                override fun onClick(it: View) {
                }
            }.initNoneClickable()
        }

        fabShare.setOnClickListener {
            try {
                shareData()

            } catch (e: Exception) {
                object : SnackbarHelper("مشکلی پیش اومد", "تلاش مجدد", it) {
                    override fun onClick(it: View) {
                        shareData()
                    }
                }.initByClickable()
            }
        }

        fabLink.setOnClickListener {
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(browserIntent)
            } catch (e: Exception) {
                object : SnackbarHelper("مرورگر در دسترس نیست","",it){
                    override fun onClick(it: View) {}
                }.initNoneClickable()
            }
        }
    }

    private fun initToolbar() {
        val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val coll_toolbar =
            findViewById<View>(R.id.toolbar_layout) as CollapsingToolbarLayout
        coll_toolbar.title = "  "
        coll_toolbar.setContentScrimColor(Color.parseColor("#C43D56"))
    }

    private fun initScrollViewAnim() {
        rootScrollView.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY >= 195 && showAnim) {
                MyAnimation(R.anim.slide_in_bottom_to_top, txtTtitle, applicationContext)
                txtTtitle.visibility = View.VISIBLE
                showAnim = false
            }
            if (scrollY < 195 && !showAnim) {
                MyAnimation(R.anim.slide_in_top_to_bottom, txtTtitle, applicationContext)
                txtTtitle.visibility = View.GONE
                showAnim = true
            }
        }
    }

    /**
     * share date function
     */
    private fun shareData() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
        val shareMessage = title + "\n \n" + Html.fromHtml(desc)
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        shareIntent.putExtra(Intent.EXTRA_TITLE, title)
        startActivity(Intent.createChooser(shareIntent, "choose one"))
    }

    /**
     * save data in clipboard for use in copy data
     */
    private fun saveTitleAndDescInClipboard() {
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", title + "\n \n" + Html.fromHtml(desc))
        clipboard.setPrimaryClip(clip)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
