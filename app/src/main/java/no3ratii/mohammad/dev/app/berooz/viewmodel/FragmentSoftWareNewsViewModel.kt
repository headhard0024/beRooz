package no3ratii.mohammad.dev.app.berooz.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser
import no3ratii.mohammad.dev.app.berooz.base.listener.AReadNews
import no3ratii.mohammad.dev.app.berooz.data.rxJava.AUpdateItems
import kotlin.collections.ArrayList

class FragmentSoftWareNewsViewModel(activity: Activity) {

    var itemList = MutableLiveData<ArrayList<RssParser.Item>>()
    var images = MutableLiveData<ArrayList<String>>()

    init {
        initialize(activity)
    }

    private fun initialize(activity: Activity) {
        object : AReadNews() {
            override fun onSuccess(
                itemList: ArrayList<RssParser.Item>?,
                images: ArrayList<String>
            ) {
                this@FragmentSoftWareNewsViewModel.itemList.value = itemList
                this@FragmentSoftWareNewsViewModel.images.value = images
            }
        }.read(activity , "https://www.itna.ir/rssir.,ltizt4,,ik1pxlne..bt2bc5.tcdtz7d.xml")

        object : AUpdateItems(){
            override fun upDate() {
                object : AReadNews() {
                    override fun onSuccess(
                        itemList: ArrayList<RssParser.Item>?,
                        images: ArrayList<String>
                    ) {
                        this@FragmentSoftWareNewsViewModel.itemList.value = itemList
                        this@FragmentSoftWareNewsViewModel.images.value = images
                    }
                }.read(activity , "https://www.itna.ir/rssir.,ltizt4,,ik1pxlne..bt2bc5.tcdtz7d.xml")
            }
        }.upDate(60)
    }
}