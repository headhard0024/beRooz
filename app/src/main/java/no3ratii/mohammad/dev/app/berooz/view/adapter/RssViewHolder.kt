package no3ratii.mohammad.dev.app.berooz.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.rss_item.view.*
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.IntervalTimeHelper
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.PersonalHelper
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser
import no3ratii.mohammad.dev.app.berooz.base.listener.IAdapterItemOnClick
import no3ratii.mohammad.dev.app.notebook.base.helper.CirculeRevealHelper

class RssViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): RssViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.rss_item, parent, false)
            return RssViewHolder(view)
        }

        fun bind(
            rssItem: RssParser.Item,
            imageItem: String,
            holder: RssViewHolder,
            position: Int
        ) {
            holder.itemView.txtTitle.text = rssItem.title
            holder.itemView.txtCategory.text = rssItem.category

//            initCategoryBackground(rssItem.category, holder.itemView)
            initCircleReveal(holder.itemView)

            holder.itemView.txtCategory.setBackgroundResource(
                PersonalHelper.categoryBackgrond(
                    rssItem.category
                )
            )

            val timeHeler = IntervalTimeHelper()
            val parsData = timeHeler.start(rssItem.pubData)
            holder.itemView.txtDate.text = timeHeler.currentTime(parsData)

            holder.itemView.img.clipToOutline = true
            Glide
                .with(holder.itemView)
                .load(imageItem)
                .into(holder.itemView.img)

            holder.itemView.root.setOnClickListener {
//                CirculeRevealHelper(it, R.color.black, R.color.white).init()
                itemOnClick.onClick(rssItem, imageItem)
            }
        }

        @SuppressLint("ClickableViewAccessibility")
        private fun initCircleReveal(itemView: View) {
            itemView.root.setOnTouchListener { view, motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        CirculeRevealHelper(view, R.color.CCT50, R.color.white)
                            .position(motionEvent.x.toInt(), motionEvent.y.toInt())
                            .init()
                    }
                }
                return@setOnTouchListener false
            }
        }

        private lateinit var itemOnClick: IAdapterItemOnClick
        fun setOnClick(itemOnClick: IAdapterItemOnClick) {
            this.itemOnClick = itemOnClick
        }

        private fun initCategoryBackground(category: String, itemView: View) {
            if (category == "بازی") {
                itemView.txtCategory.setBackgroundResource(R.drawable.category_red_bg)
            }
        }
    }
}