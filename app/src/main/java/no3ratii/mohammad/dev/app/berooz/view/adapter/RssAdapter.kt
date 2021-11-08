package no3ratii.mohammad.dev.app.berooz.view.adapter

import android.annotation.SuppressLint

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser

 class RssAdapter : ListAdapter<RssParser.Item,RssViewHolder>(REPO_COMPARATOR) {

    private var rssList = emptyList<RssParser.Item>()
    private var imageList = emptyList<String>()

    fun getUserList(userList : List<RssParser.Item> , images : List<String>){
        this.rssList = userList
        this.imageList = images
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssViewHolder {
        return RssViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return rssList.size
    }

    override fun onBindViewHolder(holder: RssViewHolder, position: Int) {
        val item = rssList[position]
        val image = imageList[position]

        RssViewHolder.bind(item,image,holder,position)
    }

    companion object {
         val REPO_COMPARATOR = object : DiffUtil.ItemCallback<RssParser.Item>() {
            override fun areItemsTheSame(oldItem: RssParser.Item, newItem: RssParser.Item): Boolean =
                oldItem.title == newItem.title

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: RssParser.Item, newItem: RssParser.Item): Boolean =
                oldItem == newItem
        }
    }
}