package com.monzo.articles.data.state

import com.monzo.articles.data.model.Article

sealed class ArticlesState {

    data class LoadData(
            val refreshing: Boolean = true,
            val articles: List<Article> = emptyList()
    ) : ArticlesState()

    data class ShowDetail(val article: Article) : ArticlesState()
}