package ir.zamen.zamen.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(fragmentManager: FragmentManager, behavior: Int) : FragmentPagerAdapter(fragmentManager, behavior) {
    var listFragment = ArrayList<Fragment>()
    var listTitle = ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        return listFragment.get(position)
    }

    override fun getCount(): Int {
        return listFragment.size
    }

    fun addFragment(fragment: Fragment , title:String) {
        listFragment.add(fragment)
        listTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listTitle[position]
    }
}