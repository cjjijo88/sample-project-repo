package com.example.newsportal.di

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentStateManagerControl
import com.example.newsportal.data.api.NetworkModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @Author: Jijo
 * @Date: 05-02-2022
 */

@Module(includes = [NetworkModule::class, PersistenceModule::class])
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    @AppContext
    fun providesContext(): Context {
        return app
    }
}