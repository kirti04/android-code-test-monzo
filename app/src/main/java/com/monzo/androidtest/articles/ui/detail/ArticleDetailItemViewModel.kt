package com.monzo.androidtest.articles.ui.detail

import com.monzo.androidtest.R
import com.monzo.androidtest.articles.data.model.Article
import com.monzo.androidtest.common.recyclerview.RecyclerViewAdapter.ItemViewModel

data class ArticleDetailItemViewModel(private val article: Article) : ItemViewModel(R.layout.item_article_detail) {

    val thumbnail = article.thumbnail
    val title = article.title
    val body = article.body
}