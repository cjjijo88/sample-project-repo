package com.example.newsportal.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsportal.data.model.ArticleData
import com.example.newsportal.data.repository.NewsDataRepository
import io.realm.RealmResults
import javax.inject.Inject

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
class NewsDetailsViewModel @Inject constructor(private val newsRepository: NewsDataRepository) :
    ViewModel() {

    fun getArticleDetailItem(url: String): RealmResults<ArticleData> = newsRepository.getRepoDataItem(url)
}