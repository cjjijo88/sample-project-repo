package com.example.newsportal.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * @Author: Jijo
 * @Date: 05-02-2022
 */
open class RootData (

    @field:SerializedName("name")
    var name: String? = null,

    @field:SerializedName("id")
    var id: String? = null
) : RealmObject()