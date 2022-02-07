package com.example.newsportal.ui.view

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsportal.NewsDemoApp
import com.example.newsportal.R
import com.example.newsportal.data.model.ArticleData
import com.example.newsportal.databinding.ActivityMainBinding
import com.example.newsportal.di.ActivityModule
import com.example.newsportal.ui.adapter.NewsListDataAdapter
import com.example.newsportal.ui.viewmodel.NewsListViewModel
import com.example.newsportal.utils.ActivityRouter
import io.realm.OrderedCollectionChangeSet
import io.realm.OrderedRealmCollectionChangeListener
import io.realm.RealmResults
import io.realm.mongodb.App
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: NewsListViewModel
    @Inject
    lateinit var router : ActivityRouter
    private lateinit var adapter: NewsListDataAdapter
    private lateinit var layoutManager: LinearLayoutManager
    val Activity.app: NewsDemoApp get() = application as NewsDemoApp
    val component by lazy { app.component.plusActivityModule(ActivityModule(this)) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)
        val context: Context = this
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        viewModel.repo
        binding.setVm(viewModel)
        layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setLayoutManager(layoutManager)
        adapter = NewsListDataAdapter(viewModel.repos!!,router)
        binding.recyclerView.setAdapter(adapter)
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!viewModel.hasMore()) {
                    return
                }
                val currentPage =
                    (layoutManager.findLastCompletelyVisibleItemPosition() + 1) / 10
                if (currentPage >= viewModel.getPage()) {
                    viewModel.loadForPage(currentPage + 1)
                }
            }
        })
        viewModel.loadForPage(1)
        viewModel.repos?.addChangeListener(changeListener)
    }


    private val changeListener: OrderedRealmCollectionChangeListener<RealmResults<ArticleData>> =
        object : OrderedRealmCollectionChangeListener<RealmResults<ArticleData>> {
            override fun onChange(
                collection: RealmResults<ArticleData>,
                changeSet: OrderedCollectionChangeSet?
            ) {
                // `null`  means the async query returns the first time.
                if (changeSet == null) {
                    adapter.notifyDataSetChanged()
                    return
                }
                // For deletions, the adapter has to be notified in reverse order.
                val deletions =
                    changeSet.deletionRanges
                for (i in deletions.indices.reversed()) {
                    val range = deletions[i]
                    adapter.notifyItemRangeRemoved(range.startIndex, range.length)
                }
                val insertions =
                    changeSet.insertionRanges
                for (range in insertions) {
                    adapter.notifyItemRangeInserted(range.startIndex, range.length)
                }
                val modifications =
                    changeSet.changeRanges
                for (range in modifications) {
                    adapter.notifyItemRangeChanged(range.startIndex, range.length)
                }
            }
        }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}