package com.example.newsportal.utils

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
object BindingUtils {
    @BindingAdapter("bind:visible")
    @JvmStatic
    fun bindVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @BindingAdapter("bind:imgUrl")
    @JvmStatic
    fun bindImageUrl(imageView : ImageView, imgUrl: String?) {
        val imgUri = Uri.parse(imgUrl)
        Glide.with(imageView.context).load(imgUri).into(imageView)
    }
}