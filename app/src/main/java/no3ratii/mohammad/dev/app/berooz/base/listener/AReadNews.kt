package no3ratii.mohammad.dev.app.berooz.base.listener

import android.app.Activity
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser
import java.util.concurrent.Executors

abstract class AReadNews {
    fun read(activity: Activity , url : String){
        Executors.newSingleThreadExecutor().submit(Runnable {
            val reader =
                RssParser(url)
            activity.runOnUiThread {
                val rssItems = reader.items
                val images = reader.imageList
                onSuccess(rssItems , images)
            }
        })
    }
    abstract fun onSuccess(itemList: ArrayList<RssParser.Item>? , images:ArrayList<String>)
}