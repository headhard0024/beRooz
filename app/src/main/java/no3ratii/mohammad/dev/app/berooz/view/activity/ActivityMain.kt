package no3ratii.mohammad.dev.app.berooz.view.activity

import SnackbarHelper
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.aminography.choosephotohelper.ChoosePhotoHelper
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import ir.zamen.zamen.view.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.navigation.*
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.DataStore
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.GlideHelper
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.HelperNewWork
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.StartActivityHelper
import no3ratii.mohammad.dev.app.berooz.base.listener.IBottomShotRespons
import no3ratii.mohammad.dev.app.berooz.view.bottomSheet.ScoreBottomSheet
import no3ratii.mohammad.dev.app.berooz.view.fragment.*
import no3ratii.mohammad.dev.app.notebook.base.helper.CirculeRevealHelper


class ActivityMain : AppCompatActivity() {

    private var choosePhotoHelper: ChoosePhotoHelper? = null

    @SuppressLint("RtlHardcoded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        //check NetWork State
        checkNetWorkState()

        // set onClick and other
        initialize()

        setTabView()
        setUserImageFromDataStore()

        //for first use and set user image in edit itme
        setUserImage(savedInstanceState)

    }

    private fun checkNetWorkState() {
        if (HelperNewWork.readStatus() == 3) {
            object : SnackbarHelper("اینترنت در دسترس نیست", "باشه", root) {
                override fun onClick(it: View) {}
            }.initByClickable()
        }

    }

    private fun setUserImageFromDataStore() {
        val imageUrl = DataStore.getValue("IMAGE_URL")
        imageUrl.asLiveData().observe(this as LifecycleOwner, Observer {
            if (!it.equals("")) {
                GlideHelper(imgUserImage, it!!, R.drawable.ic_baseline_person).bilde()
                imgChooseImage.setImageResource(R.drawable.ic_baseline_edit)
                imgChooseImage.setPadding(15, 15, 15, 15)
            } else {
                imgUserImage.setImageResource(R.drawable.ic_baseline_person)
                imgChooseImage.setImageResource(R.drawable.ic_baseline_add)
            }
        })
    }

    private fun initialize() {

        // on click listeners
        initOnClickListener()

        imgHamburgerMenu.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }

    }

    private fun initOnClickListener() {
        laySelectImage.setOnClickListener {
            choosePhotoHelper!!.showChooser()
        }

        layCall.setOnClickListener {
            Toast.makeText(baseContext, "", Toast.LENGTH_SHORT).show()
        }

        layCall.setOnClickListener {
            //use helper for Start Activity
            StartActivityHelper(it.context, ActivityCall())
                .setReveal(it, R.color.whiteTranspanet80, R.color.colorPrimary)
                .finish()
                .init()
        }

        layOther.setOnClickListener {
            setReveal(it)
            val url = "myket://developer/no3ratii.mohammad.dev.app"
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(url)
            startActivity(intent)
        }

        layScore.setOnClickListener {
            setReveal(it)
            //set bottom sheet manager
            val manager: FragmentManager =
                (it.context as AppCompatActivity).supportFragmentManager
            // call ScoreBottomSheet class and set new instance
            ScoreBottomSheet.newInstance()
                .show(manager, "CustomBottomSheetDialogFragment")
            // call ScoreBottomSheet listener for return value and start App Score in myket
            ScoreBottomSheet.setClicked(object : IBottomShotRespons {
                override fun onEditTextValue(value: String) {
                    val url = "myket://comment?id=no3ratii.mohammad.dev.app.beRooz"
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }
            })
        }
    }

    private fun setReveal(it: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CirculeRevealHelper(it, R.color.whiteTranspanet80, R.color.colorPrimary)
                .radius(10)
                .init()
        }
    }

    private fun setTabView() {
        val adapter = ViewPagerAdapter(supportFragmentManager, 0)
        adapter.addFragment(SoftWareNewsFragment(), "نرم افزار")
        adapter.addFragment(ICTNewsFragment(), "مديريت ICT")
        adapter.addFragment(DevelopNewsFragment(), "برنامه نویسی")
        adapter.addFragment(SpaceNewsFragment(), "ماهواره و فضا")
        adapter.addFragment(ConnectionNewsFragment(), "ارتباطات")
        adapter.addFragment(NetWorkAndSecurityNewsFragment(), "شبکه و امنیت")
        adapter.addFragment(JobNewsFragment(), "فرصت های شغلی")
        adapter.addFragment(SocialNetWorkNewsFragment(), "شبکه اجتماعی")
        adapter.addFragment(InternetNewsFragment(), "اینترنت")
        adapter.addFragment(MoblieNewsFragment(), "موبایل")
        adapter.addFragment(RoboticNewsFragment(), "رباتیک")
        adapter.addFragment(GameNewsFragment(), "بازی")
        adapter.addFragment(TechnologyNewsFragment(), "فناوری")
        adapter.addFragment(AllNewsFragment(), "سر خط")
        viewPager.adapter = adapter
        boxTabs.setupWithViewPager(viewPager)
        boxTabs.tabMode = MODE_SCROLLABLE
        viewPager.currentItem = 13
    }

    private fun setUserImage(savedInstanceState: Bundle?) {
        choosePhotoHelper = ChoosePhotoHelper.with(this)
            .asFilePath()
            .withState(savedInstanceState)
            .build(object : ChoosePhotoCallback<String> {
                override fun onChoose(photo: String?) {
                    if (photo.equals(null)) {
                        imgUserImage.setImageResource(R.drawable.ic_baseline_person)
                    } else {
                        GlideHelper(
                            imgUserImage,
                            photo!!,
                            R.drawable.ic_baseline_person
                        ).bilde()
                        DataStore.setValue(photo, "IMAGE_URL")
                    }
                }
            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        choosePhotoHelper!!.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        choosePhotoHelper!!.onRequestPermissionsResult(
            requestCode,
            permissions as Array<String>, grantResults
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        choosePhotoHelper!!.onSaveInstanceState(outState)
    }
}