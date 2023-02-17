package com.putrash.saltassesment.arch.ui.news

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.putrash.common.EventObserver
import com.putrash.data.model.Article
import com.putrash.saltassesment.R
import com.putrash.saltassesment.arch.adapter.NewsAdapter
import com.putrash.saltassesment.arch.vm.MainViewModel
import com.putrash.saltassesment.base.BaseFragment
import com.putrash.saltassesment.databinding.FragmentNewsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<FragmentNewsBinding, MainViewModel>(
    FragmentNewsBinding::inflate
) {
    override val viewModel: MainViewModel by viewModel()
    private val requestManager by lazy { Glide.with(this) }
    private val adapter by lazy { NewsAdapter(layoutInflater, requestManager, ::onItemNewsClick) }

    override fun initView(view: View, savedInstaceState: Bundle?) {
        binding.rvNews.adapter = adapter
        binding.tvMore.setOnClickListener {
            findNavController().navigate(R.id.action_newsFragment_to_newsDiscoverFragment)
        }
        viewModel.getHeadlines()
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.articles.observe(viewLifecycleOwner, EventObserver { data ->
            requestManager.load(data[0].urlToImage).into(binding.ivHeader)
            binding.tvHeaderTitle.text = data[0].title
            binding.tvReadMore.setOnClickListener {
                val bundle = bundleOf("article" to data[0])
                findNavController().navigate(R.id.action_newsFragment_to_newsDetailFragment, bundle)
            }
            adapter.submitList(data.drop(1))
        })
    }

    private fun onItemNewsClick(article: Article) {
        val bundle = bundleOf("article" to article)
        findNavController().navigate(R.id.action_newsFragment_to_newsDetailFragment, bundle)
    }

}