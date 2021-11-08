package no3ratii.mohammad.dev.app.berooz.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser
import no3ratii.mohammad.dev.app.berooz.base.listener.AReadNews
import no3ratii.mohammad.dev.app.berooz.data.rxJava.AUpdateItems
import kotlin.collections.ArrayList

class FragmentAllNewsViewModel(activity: Activity) {

    var itemList = MutableLiveData<ArrayList<RssParser.Item>>()
    var images = MutableLiveData<ArrayList<String>>()

    init {
        update(activity)
    }

    private fun update(activity: Activity) {
        object : AReadNews() {
            override fun onSuccess(
                itemList: ArrayList<RssParser.Item>?,
                images: ArrayList<String>
            ) {
                this@FragmentAllNewsViewModel.itemList.value = itemList
                this@FragmentAllNewsViewModel.images.value = images
            }
        }.read(activity , "https://www.itna.ir/rssew.skj1zjyss1rhx2k4m..9ji9b7.jbgjzqg.xml")

        object : AUpdateItems(){
            override fun upDate() {
                object : AReadNews() {
                    override fun onSuccess(
                        itemList: ArrayList<RssParser.Item>?,
                        images: ArrayList<String>
                    ) {
                        //
                        this@FragmentAllNewsViewModel.itemList.value = itemList
                        this@FragmentAllNewsViewModel.images.value = images
                    }
                }.read(activity , "https://www.itna.ir/rssew.skj1zjyss1rhx2k4m..9ji9b7.jbgjzqg.xml")
            }
        }.upDate(15)
    }
}