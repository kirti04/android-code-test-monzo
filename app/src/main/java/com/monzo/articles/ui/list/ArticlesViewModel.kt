package com.monzo.articles.ui.list

import com.monzo.articles.data.repo.ArticlesRepository
import com.monzo.articles.data.model.Article
import com.monzo.articles.data.state.ArticlesState
import com.monzo.common.BaseViewModel
import com.monzo.common.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.joda.time.DateTime
import org.joda.time.Weeks

class ArticlesViewModel(private val repository: ArticlesRepository
) : BaseViewModel<ArticlesState>(ArticlesState.LoadData()) {

    init {
        loadData()
    }

    fun getListItems(articles: List<Article>) = articles.sortedByDescending { it.published }.groupBy { it.published.weekOfWeekyear() }.map {
        val weekDateRange = DateTime.now().withTimeAtStartOfDay().minus(Weeks.ONE).plusDays(1)
        val isDateWithinWeek = it.key.dateTime.isAfter(weekDateRange)
        ArticlesItemViewModel(isDateWithinWeek, it.value, ::showArticleDetail)
    }

    private fun showArticleDetail(article: Article) {
        setState { ArticlesState.ShowDetail(article = article) }
    }

    fun onRefresh() {
        setState { ArticlesState.LoadData().copy(refreshing = true) }
        loadData()
    }

    private fun loadData() {
        disposables += repository.latestFintechArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { articles ->
                    setState { ArticlesState.LoadData().copy(refreshing = false, articles = articles) }
                }
    }
}
