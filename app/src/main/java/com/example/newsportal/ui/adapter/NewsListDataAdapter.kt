package com.example.newsportal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.newsportal.R
import com.example.newsportal.data.model.ArticleData
import com.example.newsportal.databinding.NewsListItemBinding
import com.example.newsportal.ui.viewmodel.NewsItemViewModel
import com.example.newsportal.utils.ActivityRouter
import io.realm.RealmResults

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
internal class NewsListDataAdapter(
    private val dataList: RealmResults<ArticleData>,
    private val router: ActivityRouter
) :
    RecyclerView.Adapter<NewsListDataAdapter.ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: NewsListItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.news_list_item,
                parent,
                false
            )
        return ItemViewHolder(binding, NewsItemViewModel())
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal inner class ItemViewHolder(
        private val binding: NewsListItemBinding,
        private val viewModel: NewsItemViewModel
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repoModel: ArticleData?) {
            viewModel.articlesItem = repoModel
            binding.vm = viewModel
            binding.executePendingBindings()
            binding.parent.setOnClickListener {
                router.startNewsDetailActivity(repoModel!!)
            }
        }

    }

}