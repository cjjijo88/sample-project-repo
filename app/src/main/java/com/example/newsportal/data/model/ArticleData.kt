package com.example.newsportal.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

/**
 * @Author: Jijo
 * @Date: 05-02-2022
 */
open class ArticleData : RealmObject(), Serializable {

    var publishedAt: String? = null
    var author: String? = null
    var urlToImage: String? = null
    var description: String? = null
    var source: RootData? = null
    var title: String? = null
    @PrimaryKey
    var url: String? = null
    var content: String? = null
}

