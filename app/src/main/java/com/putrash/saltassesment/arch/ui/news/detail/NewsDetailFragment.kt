package com.putrash.saltassesment.arch.ui.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.putrash.data.model.Article
import com.putrash.saltassesment.R
import com.putrash.saltassesment.arch.vm.MainViewModel
import com.putrash.saltassesment.base.BaseFragment
import com.putrash.saltassesment.databinding.FragmentNewsDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding, MainViewModel>(
    FragmentNewsDetailBinding::inflate
) {
    override val viewModel: MainViewModel by viewModel()

    override fun initView(view: View, savedInstaceState: Bundle?) {
        val article = initBundle()
        binding.apply {
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            tvHeaderTitle.text = article.title
            tvContent.text = article.description
        }
        Glide.with(this).load(article.urlToImage).into(binding.ivHeader)

    }

    private fun initBundle(): Article {
        return arguments?.getParcelable("article") ?: Article()
    }
}