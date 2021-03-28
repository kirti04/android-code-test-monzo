package com.monzo.articles.data.state

import com.monzo.articles.data.model.Article

data class ArticleDetailState(
        val refreshing: Boolean = true,
        val article: Article? = null
)