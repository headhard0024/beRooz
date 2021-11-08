package no3ratii.mohammad.dev.app.berooz.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_all_news.*
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.berooz.base.G
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.PersonalHelper
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser
import no3ratii.mohammad.dev.app.berooz.base.listener.IAdapterItemOnClick
import no3ratii.mohammad.dev.app.berooz.view.activity.ActivityShowDetails
import no3ratii.mohammad.dev.app.berooz.view.adapter.AllNewsAdapter
import no3ratii.mohammad.dev.app.berooz.view.adapter.RssViewHolder
import no3ratii.mohammad.dev.app.berooz.viewmodel.FragmentAllNewsViewModel

class AllNewsFragment : Fragment() {

    private lateinit var allNewViewModel: FragmentAllNewsViewModel
    private val adapter = AllNewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allNewViewModel = FragmentAllNewsViewModel(requireActivity())
        shimmerLayout.startShimmerAnimation()
        currentTime()
        initAdapter()
        adapterItemListener()
        layDate.setBackgroundResource(PersonalHelper.dateBackgrond(G.currentPersianTime.shMonth))
    }

    private fun adapterItemListener() {
        RssViewHolder.setOnClick(object : IAdapterItemOnClick {
            override fun onClick(items: RssParser.Item, imageUrl: String) {
                val intent = Intent(G.context, ActivityShowDetails::class.java)
                intent.putExtra("TITLE", items.title)
                intent.putExtra("DESC", items.descrption)
                intent.putExtra("URL", imageUrl)
                intent.putExtra("LINK", items.link)
                startActivity(intent)
            }
        })
    }

    private fun initAdapter() {
        recRoot.layoutManager = GridLayoutManager(G.context, 1, RecyclerView.VERTICAL, false)
        recRoot.adapter = adapter
        setAdapterItems()
    }

    private fun setAdapterItems() {
        allNewViewModel.images.observe(viewLifecycleOwner, Observer { images ->
            allNewViewModel.itemList.observe(viewLifecycleOwner, Observer {
                if (images.isNotEmpty() && it.isNotEmpty()) {
                    adapter.getRssList(it, images)
                    recRoot.visibility = View.VISIBLE
                    layDate.visibility = View.GONE
                    shimmerLayout.stopShimmerAnimation()
                    shimmerLayoutContiner.visibility = View.GONE
                }
            })
        })
    }

    private fun currentTime() {
        txtYear.text = G.currentPersianTime.shYear.toString()
        txtMonthName.text = G.currentPersianTime.monthName()
        txtShDay.text = G.currentPersianTime.shDay.toString()
        txtDayName.text = G.currentPersianTime.dayName()
    }
}