package com.putrash.saltassesment.arch.ui.news.discover

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.putrash.saltassesment.arch.vm.MainViewModel
import com.putrash.saltassesment.base.BaseFragment
import com.putrash.saltassesment.databinding.FragmentNewsDiscoverBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsDiscoverFragment() : BaseFragment<FragmentNewsDiscoverBinding, MainViewModel>(
    FragmentNewsDiscoverBinding::inflate
) {
    private val titles = arrayOf("General", "Health", "Science", "Sports")
    override val viewModel: MainViewModel by viewModel()

    override fun initView(view: View, savedInstaceState: Bundle?) {
        binding.viewPager.adapter = DiscoverAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}