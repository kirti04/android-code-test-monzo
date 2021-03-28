package com.monzo.articles.ui.list

import com.monzo.androidtest.R
import com.monzo.articles.data.model.Article
import com.monzo.common.recyclerview.RecyclerViewAdapter

class ArticleItemViewModel(
        private val article: Article,
        private val showDetail: (Article) -> Unit
) : RecyclerViewAdapter.ItemViewModel(R.layout.list_item_article) {

    val thumbnail = article.thumbnail
    val isRounded = true
    val title = article.title
    val date: String = article.published.toString("dd/MM/yy")

    fun onClick() {
        showDetail.invoke(article)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as ArticleItemViewModel

        if (date != other.date) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}