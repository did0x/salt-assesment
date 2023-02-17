package com.putrash.saltassesment.arch.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.RequestManager
import com.putrash.data.model.Article
import com.putrash.saltassesment.base.BaseListAdapter
import com.putrash.saltassesment.base.BaseViewHolder
import com.putrash.saltassesment.databinding.ItemNewsBinding

class NewsDiscoverAdapter(
    layoutInflater: LayoutInflater,
    private val glide: RequestManager,
    private val onClickListener: (Article) -> Unit
) : BaseListAdapter<Article, ItemNewsBinding, NewsDiscoverAdapter.NewsDiscoverViewHolder>(
    layoutInflater, ItemNewsBinding::inflate, onClickListener, NewsDiffCallback
) {

    override fun itemViewHolder(
        viewBinding: ItemNewsBinding,
        viewType: Int,
        onClickListener: (Article) -> Unit,
    ) = NewsDiscoverViewHolder(viewBinding)

    override fun onBindViewHolder(holder: NewsDiscoverViewHolder, position: Int) {
        return holder.onBind(getItem(position))
    }

    inner class NewsDiscoverViewHolder(private val binding: ItemNewsBinding) : BaseViewHolder<Article>(binding.root) {
        override fun onBind(item: Article) {
            binding.tvNewsTitle.text = item.title
            binding.tvNewsDescription.text = item.description
            glide.load(item.urlToImage).into(binding.ivThumbnail)
            binding.root.setOnClickListener {
                onClickListener(item)
            }
        }
    }

    object NewsDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
        override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem.title == newItem.title
    }
}