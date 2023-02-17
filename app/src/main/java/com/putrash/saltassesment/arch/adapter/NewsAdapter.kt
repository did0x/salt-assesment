package com.putrash.saltassesment.arch.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.putrash.data.model.Article
import com.putrash.saltassesment.base.BaseListAdapter
import com.putrash.saltassesment.base.BaseViewHolder
import com.putrash.saltassesment.databinding.ItemBreakingNewsBinding

class NewsAdapter(
    layoutInflater: LayoutInflater,
    private val glide: RequestManager,
    private val onClickListener: (Article) -> Unit
) : BaseListAdapter<Article, ItemBreakingNewsBinding, NewsAdapter.NewsViewHolder>(
    layoutInflater, ItemBreakingNewsBinding::inflate, onClickListener, NewsDiffCallback
) {

    override fun itemViewHolder(
        viewBinding: ItemBreakingNewsBinding,
        viewType: Int,
        onClickListener: (Article) -> Unit,
    ) = NewsViewHolder(viewBinding)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        return holder.onBind(getItem(position))
    }

    inner class NewsViewHolder(private val binding: ItemBreakingNewsBinding) : BaseViewHolder<Article>(binding.root) {
        override fun onBind(item: Article) {
            binding.apply {
                tvNewsTitle.text = item.title
                tvNewsDescription.text = item.description
                glide.load(item.urlToImage).into(ivThumbnail)
                root.setOnClickListener {
                    onClickListener(item)
                }
            }
        }
    }

    object NewsDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem.title == newItem.title
    }
}