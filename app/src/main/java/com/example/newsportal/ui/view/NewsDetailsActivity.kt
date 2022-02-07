package com.example.newsportal.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.newsportal.NewsDemoApp
import com.example.newsportal.R
import com.example.newsportal.databinding.ActivityNewsDetailsBinding
import com.example.newsportal.di.ActivityModule
import com.example.newsportal.ui.viewmodel.NewsDetailsViewModel
import io.realm.mongodb.App
import javax.inject.Inject

class NewsDetailsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewmodel : NewsDetailsViewModel
    val Activity.app: NewsDemoApp get() = application as NewsDemoApp
    val component by lazy { app.component.plusActivityModule(ActivityModule(this)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding = DataBindingUtil.setContentView<ActivityNewsDetailsBinding>(this, R.layout.activity_news_details)
        component.inject(this)
        val data = viewmodel.getArticleDetailItem(intent.getStringExtra(KEY_DATA).toString())
        dataBinding.vm =data.get(0)
        //val data = intent.getStringExtra(KEY_DATA)?.let { viewmodel.getArticleDetailItem(it) }
        //dataBinding.vm =data.get(0)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    companion object {
        @JvmField
        var KEY_DATA: String = "news_data"
    }
}
