package com.example.newsportal.data.api

import android.content.Context
import com.example.newsportal.BuildConfig
import com.example.newsportal.di.AppContext
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient?,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory?,
        gsonConverterFactory: GsonConverterFactory?
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    @Inject
    fun getOkHttpClient(@AppContext context: Context): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        val cacheSize = CACHE_SIZE * 1024 * 1024
        return OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http"), cacheSize.toLong()))
            .retryOnConnectionFailure(false)
            .readTimeout(
                READ_TIMEOUT.toLong(),
                TimeUnit.MILLISECONDS
            )
            .connectTimeout(
                CONNECTION_TIMEOUT.toLong(),
                TimeUnit.MILLISECONDS
            )
            .addInterceptor(logger)
            .build()
    }

    @Singleton
    @Provides
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    companion object {
        private const val CACHE_SIZE = 4
        private const val READ_TIMEOUT = 100 * 1000
        private const val CONNECTION_TIMEOUT = 100 * 1000
    }
}
