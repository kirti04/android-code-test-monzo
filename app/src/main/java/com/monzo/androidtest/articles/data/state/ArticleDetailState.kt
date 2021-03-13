package com.monzo.androidtest.articles.data.state

import com.monzo.androidtest.articles.data.model.Article

data class ArticleDetailState(
        val refreshing: Boolean = true,
        val article: Article? = null
)