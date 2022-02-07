package com.example.newsportal.data.repository

import com.example.newsportal.data.api.NewsApiService
import com.example.newsportal.data.model.ArticleData
import com.example.newsportal.data.model.BaseData
import com.example.newsportal.db.LocalDatabase
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import io.realm.RealmResults
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
@Singleton
class NewsDataRepository @Inject constructor(db: LocalDatabase, apiService: NewsApiService) {
    private val db: LocalDatabase
    private val apiService: NewsApiService

    fun getRepoDataList() : RealmResults<ArticleData> = db.getRepoListDb()

    fun getRepoDataItem(url: String): RealmResults<ArticleData> = db.getRepoItemDb(url)

    fun fetchAndPersistRepos(page: Int): Observable<BaseData> {
        return apiService.getNewsData("crypto", "2022-02-05", "publishedAt"
            ,"214692b82981453a906e840e757ee522")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map(object :
                Function<BaseData, BaseData> {
                @Throws(Exception::class)
                override fun apply(baseResponseList: BaseData): BaseData {
                    return baseResponseList
                }
            })
            .doOnNext(Consumer<BaseData> { repoModelList -> db.saveRepos(repoModelList) })
    }

    init {
        this.db = db
        this.apiService = apiService
    }
}