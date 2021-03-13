package com.monzo.androidtest.articles.data.state

import com.monzo.androidtest.articles.data.model.Article

sealed class ArticlesState {

    data class LoadData(
            val refreshing: Boolean = true,
            val articles: List<Article> = emptyList()
    ) : ArticlesState()

    data class ShowDetail(val article: Article) : ArticlesState()
}