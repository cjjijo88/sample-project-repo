package com.example.newsportal.di

import com.example.newsportal.ui.view.MainActivity
import com.example.newsportal.ui.view.NewsDetailsActivity
import dagger.Subcomponent

/**
 * @Author: Jijo
 * @Date: 05-02-2022
 */
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: NewsDetailsActivity)
}