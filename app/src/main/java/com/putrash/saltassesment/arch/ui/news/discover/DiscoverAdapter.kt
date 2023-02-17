package com.putrash.saltassesment.arch.ui.news.discover

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DiscoverAdapter (fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val titles = arrayOf("General", "Health", "Science", "Sports")

    override fun getItemCount(): Int {
        return titles.size
    }

    override fun createFragment(position: Int): Fragment {
        return DiscoverFragment(titles[position])
    }
}