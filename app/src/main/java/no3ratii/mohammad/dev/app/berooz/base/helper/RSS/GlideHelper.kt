package no3ratii.mohammad.dev.app.berooz.base.helper.RSS

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

data class GlideHelper(var imageView: ImageView, var url: String, var placeholder: Int){

    fun bilde() : GlideHelper{
        Glide.with(imageView)
            .load(url)
            .apply(RequestOptions.placeholderOf(placeholder))
            .circleCrop()
            .into(imageView)
        return this
    }

    fun bildeNoClicle() : GlideHelper{
        Glide.with(imageView)
            .load(url)
            .apply(RequestOptions.placeholderOf(placeholder))
            .into(imageView)
        return this
    }
}