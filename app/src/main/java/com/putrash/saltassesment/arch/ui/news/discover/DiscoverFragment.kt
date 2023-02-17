package com.putrash.saltassesment.arch.ui.news.discover

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.putrash.common.EventObserver
import com.putrash.data.model.Article
import com.putrash.saltassesment.R
import com.putrash.saltassesment.arch.adapter.NewsDiscoverAdapter
import com.putrash.saltassesment.arch.vm.MainViewModel
import com.putrash.saltassesment.base.BaseFragment
import com.putrash.saltassesment.databinding.FragmentNewsListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiscoverFragment(private val category: String) : BaseFragment<FragmentNewsListBinding, MainViewModel>(
    FragmentNewsListBinding::inflate
) {
    override val viewModel: MainViewModel by viewModel()
    private val requestManager by lazy { Glide.with(this) }
    private val adapter by lazy { NewsDiscoverAdapter(layoutInflater, requestManager, ::onItemNewsClick) }

    override fun initView(view: View, savedInstaceState: Bundle?) {
        binding.rvNews.adapter = adapter
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.articles.observe(viewLifecycleOwner, EventObserver { data ->
            adapter.submitList(data.drop(1))
        })
    }

    private fun onItemNewsClick(article: Article) {
        val bundle = bundleOf("article" to article)
        findNavController().navigate(R.id.action_newsDiscoverFragment_to_newsDetailFragment, bundle)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNews(category)
    }
}