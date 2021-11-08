package no3ratii.mohammad.dev.app.berooz.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser

class AllNewsAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //initialize
    private var rssList = emptyList<RssParser.Item>()
    private var imageList = emptyList<String>()

    fun getRssList(userList: List<RssParser.Item>, imageList: List<String>) {
        this.rssList = userList
        this.imageList = imageList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        // items layout
        val myHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.rss_item, parent, false)
        //first item
        val firstItem =
            LayoutInflater.from(parent.context).inflate(R.layout.rss_item_first, parent, false)
        //end item layout
        val endtItem =
            LayoutInflater.from(parent.context).inflate(R.layout.rss_item_end, parent, false)

        // set view type for call in bind
        return when (viewType) {
            // FirstItemViewHolder view holder for first item
            0 -> FirstItemViewHolder(firstItem)
            // EndItemViewHolder view holder for end item
            1 -> EndItemViewHolder(endtItem)
            // MyViewHolder view holder items
            else -> RssViewHolder(myHolder)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //logic for set binding valuse by use item view type
        when (holder.itemViewType) {
            0 -> {
                val firstItem = holder as FirstItemViewHolder
                FirstItemViewHolder.bind(firstItem)
            }
            1 -> {
                val endItem = holder as EndItemViewHolder
                endItemLogic(endItem)
            }
            else -> {
                val myViewHolder = holder as RssViewHolder
                //position -1 for dont use end item
                val item = rssList[position - 1]
                val image = imageList[position - 1]
                //start viewHolder binding
                RssViewHolder.bind(item, image, myViewHolder, position)
            }
        }
    }

    class EndItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun getItemCount(): Int {
        // plus two for use first and end item in recycler
        return rssList.size + 2
    }

    //set to items value viewType for use in CreateviewHlder and use other viewHolders
    override fun getItemViewType(position: Int): Int {
        return when {
            position < 1 -> {
                0
            }
            position == rssList.size + 1 -> {
                1
            }
            else -> {
                2
            }
        }
    }

    private fun endItemLogic(endItem: EndItemViewHolder) {

    }
}