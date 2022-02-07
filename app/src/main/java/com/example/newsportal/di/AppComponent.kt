package com.example.newsportal.di

import com.example.newsportal.NewsDemoApp
import dagger.Component
import javax.inject.Singleton

/**
 * @Author: Jijo
 * @Date: 05-02-2022
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: NewsDemoApp)
    fun plusActivityModule(activityModule: ActivityModule): ActivityComponent
}