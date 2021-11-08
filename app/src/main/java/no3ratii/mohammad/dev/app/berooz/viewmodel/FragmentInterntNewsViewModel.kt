package no3ratii.mohammad.dev.app.berooz.viewmodel

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser
import no3ratii.mohammad.dev.app.berooz.base.listener.AReadNews
import no3ratii.mohammad.dev.app.berooz.data.rxJava.AUpdateItems
import kotlin.collections.ArrayList

class FragmentInterntNewsViewModel(activity: Activity) {

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
                this@FragmentInterntNewsViewModel.itemList.value = itemList
                this@FragmentInterntNewsViewModel.images.value = images
            }
        }.read(activity , "https://www.itna.ir/rssci.uy2-s2tuu-5b1,ydk.a22a..i48l3ls273.xml")

        object : AUpdateItems(){
            override fun upDate() {
                object : AReadNews() {
                    override fun onSuccess(
                        itemList: ArrayList<RssParser.Item>?,
                        images: ArrayList<String>
                    ) {
                        this@FragmentInterntNewsViewModel.itemList.value = itemList
                        this@FragmentInterntNewsViewModel.images.value = images
                    }
                }.read(activity , "https://www.itna.ir/rssci.uy2-s2tuu-5b1,ydk.a22a..i48l3ls273.xml")
            }
        }.upDate(60)
    }
}