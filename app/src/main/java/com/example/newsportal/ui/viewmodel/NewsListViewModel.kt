package com.example.newsportal.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.newsportal.data.model.ArticleData
import com.example.newsportal.data.model.BaseData
import com.example.newsportal.data.repository.NewsDataRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.realm.RealmResults
import javax.inject.Inject

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
class NewsListViewModel @Inject constructor(private val mainRepository: NewsDataRepository) :
    ViewModel() {
    var repos: RealmResults<ArticleData>? = null
        private set
    private val hasMore = true
    private val page: ObservableField<Int>
    val showError: ObservableField<Boolean>
    val isLoading: ObservableField<Boolean>
    val repo: Unit
        get() {
            repos = mainRepository.getRepoDataList()
        }


    fun loadForPage(requestedPage: Int) {
        if (!isLoading.get()!!) {
            isLoading.set(true)
            showError.set(false)
            mainRepository.fetchAndPersistRepos(requestedPage)
                .subscribe(object : Observer<BaseData> {
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(baseModel: BaseData) { // hasMore = products.size() == 10;
                    }

                    override fun onError(e: Throwable) {
                        isLoading.set(false)
                        if (repos!!.size == 0) {
                            showError.set(true)
                        }
                        e.printStackTrace()
                    }

                    override fun onComplete() {
                        isLoading.set(false)
                        page.set(requestedPage)
                    }
                })
        }
    }

    fun getPage(): Int {
        return page.get()!!
    }

    fun hasMore(): Boolean {
        return hasMore
    }

    init {
        page = ObservableField(1)
        showError = ObservableField(false)
        isLoading = ObservableField(false)
    }
}