package com.example.newsportal.db

import com.example.newsportal.data.model.ArticleData
import com.example.newsportal.data.model.BaseData
import io.realm.Realm
import io.realm.RealmResults

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
class LocalDatabase (var realm: Realm) {

    fun getRepoListDb(): RealmResults<ArticleData> {
        return realm.where(ArticleData::class.java).findAllAsync()
    }
    fun getRepoItemDb(url: String): RealmResults<ArticleData> {
        return realm.where(ArticleData::class.java).equalTo("url", url).findAllAsync()
    }

    fun saveRepos(baseModel : BaseData) {
        realm.executeTransactionAsync { realm: Realm ->
            realm.copyToRealmOrUpdate(baseModel.articles)
        }
    }
}