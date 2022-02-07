package com.example.newsportal.utils

import android.app.Activity
import android.content.Intent
import com.example.newsportal.data.model.ArticleData
import com.example.newsportal.ui.view.NewsDetailsActivity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
@Singleton
class ActivityRouter @Inject constructor(private val activity: Activity) {
    fun startNewsDetailActivity(articlesItem: ArticleData) {
        val intent = Intent(activity, NewsDetailsActivity::class.java)
        intent.putExtra(NewsDetailsActivity.KEY_DATA, articlesItem.url)
        activity.startActivity(intent)
    }
}