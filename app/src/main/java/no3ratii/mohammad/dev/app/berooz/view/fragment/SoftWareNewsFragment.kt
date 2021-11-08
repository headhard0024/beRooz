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
import kotlinx.android.synthetic.main.fragment_other_news.*
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.berooz.base.G
import no3ratii.mohammad.dev.app.berooz.base.helper.RSS.RssParser
import no3ratii.mohammad.dev.app.berooz.base.listener.IAdapterItemOnClick
import no3ratii.mohammad.dev.app.berooz.view.activity.ActivityShowDetails
import no3ratii.mohammad.dev.app.berooz.view.adapter.RssAdapter
import no3ratii.mohammad.dev.app.berooz.view.adapter.RssViewHolder
import no3ratii.mohammad.dev.app.berooz.viewmodel.FragmentSoftWareNewsViewModel

class SoftWareNewsFragment : Fragment() {

    private lateinit var softWareNewViewModel: FragmentSoftWareNewsViewModel
    private val adapter = RssAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_other_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        softWareNewViewModel = FragmentSoftWareNewsViewModel(requireActivity())
        shimmerLayout.startShimmerAnimation()
        initAdapter()
        adapterItemListener()
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
        softWareNewViewModel.images.observe(viewLifecycleOwner, Observer { images ->
            softWareNewViewModel.itemList.observe(viewLifecycleOwner, Observer {
                if (images.isNotEmpty() && it.isNotEmpty()) {
                    adapter.getUserList(it, images)
                    recRoot.visibility = View.VISIBLE
                    shimmerLayout.stopShimmerAnimation()
                    shimmerLayoutContiner.visibility = View.GONE
                }
            })
        })
    }
}