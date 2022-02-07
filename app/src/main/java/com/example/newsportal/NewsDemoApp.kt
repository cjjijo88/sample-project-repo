package com.example.newsportal

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.newsportal.data.api.NetworkModule
import com.example.newsportal.di.*
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.realm.Realm
import javax.inject.Inject

/**
 * @Author: Jijo
 * @Date: 05-02-2022
 */
class NewsDemoApp :  Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        component.inject(this)
    }

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule())
            .persistenceModule(PersistenceModule())
            .build()
    }



   /* override fun androidInjector(): AndroidInjector<Any> {
        TODO("Not yet implemented")
    }*/

}