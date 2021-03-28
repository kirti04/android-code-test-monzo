package com.monzo.articles.ui.detail

import com.monzo.articles.data.model.Article
import com.monzo.articles.data.state.ArticleDetailState
import com.monzo.articles.data.repo.ArticlesRepository
import com.monzo.common.BaseViewModel
import com.monzo.common.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.lang.IllegalArgumentException

class ArticleDetailViewModel(private val repository: ArticlesRepository
) : BaseViewModel<ArticleDetailState>(ArticleDetailState()) {

    private lateinit var article: Article

    fun setArgs(article: Article?) {
        this.article = article ?: throw IllegalArgumentException("article must not be null")
        setState { copy(refreshing = false, article = article) }
    }

    fun getListItems(article: Article?) = if (article == null) {
        emptyList()
    } else {
        listOf(ArticleDetailItemViewModel(article))
    }

    fun onStart() {
        loadData(article.url)
    }

    private fun loadData(url: String) {
        disposables += repository.getArticle(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe { article ->
                    this.article = this.article.copy(body = article.body)
                    setState { copy(refreshing = false, article = this.article) }
                }
    }

    fun setFavourite(){

    }
}