package com.example.newsportal.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.example.newsportal.utils.ActivityRouter
import dagger.Module
import dagger.Provides

/**
 * @Author: Jijo
 * @Date: 05-02-2022
 */
@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }
    @Provides
    fun providesActivityRouter(): ActivityRouter {
        return ActivityRouter(activity)
    }

}