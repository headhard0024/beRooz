package no3ratii.mohammad.dev.app.berooz.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser
import no3ratii.mohammad.dev.app.berooz.base.listener.AReadNews
import no3ratii.mohammad.dev.app.berooz.data.rxJava.AUpdateItems
import kotlin.collections.ArrayList

class FragmentNetWorkAndSecurityNewsViewModel(activity: Activity) {

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
                this@FragmentNetWorkAndSecurityNewsViewModel.itemList.value = itemList
                this@FragmentNetWorkAndSecurityNewsViewModel.images.value = images
            }
        }.read(activity , "https://www.itna.ir/rsshi.c120z29cc0y3xu1go.t22t..ixdfafz2la.xml")

        object : AUpdateItems(){
            override fun upDate() {
                object : AReadNews() {
                    override fun onSuccess(
                        itemList: ArrayList<RssParser.Item>?,
                        images: ArrayList<String>
                    ) {
                        this@FragmentNetWorkAndSecurityNewsViewModel.itemList.value = itemList
                        this@FragmentNetWorkAndSecurityNewsViewModel.images.value = images
                    }
                }.read(activity , "https://www.itna.ir/rsshi.c120z29cc0y3xu1go.t22t..ixdfafz2la.xml")
            }
        }.upDate(60)
    }
}