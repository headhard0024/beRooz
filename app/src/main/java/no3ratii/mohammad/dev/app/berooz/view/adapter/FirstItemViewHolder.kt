package no3ratii.mohammad.dev.app.berooz.view.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rss_item_first.view.*
import no3ratii.mohammad.dev.app.berooz.base.G
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.PersonalHelper

class FirstItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun bind(
            holder: FirstItemViewHolder
        ) {
            holder.itemView.txtYear.text = G.currentPersianTime.shYear.toString()
            holder.itemView.txtMonthName.text = G.currentPersianTime.monthName()
            holder.itemView.txtShDay.text = G.currentPersianTime.shDay.toString()
            holder.itemView.txtDayName.text = G.currentPersianTime.dayName()
            holder.itemView.layRootDate.setBackgroundResource(PersonalHelper.dateBackgrond(G.currentPersianTime.shMonth))
        }
    }
}