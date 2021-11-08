package no3ratii.mohammad.dev.app.berooz.base.listener

import android.view.View
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser

interface IAdapterItemOnClick {
    fun onClick(items:RssParser.Item , imageUrl: String)
}