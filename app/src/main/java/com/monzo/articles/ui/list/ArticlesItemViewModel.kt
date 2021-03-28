package com.monzo.articles.ui.list

import com.monzo.androidtest.R
import com.monzo.articles.data.model.Article
import com.monzo.common.recyclerview.RecyclerViewAdapter.ItemViewModel

class ArticlesItemViewModel(
        private val isDateWithinWeek: Boolean,
        private val articles: List<Article>,
        private val showDetail: (Article) -> Unit
) : ItemViewModel(R.layout.item_card_container) {

    val title = if (isDateWithinWeek) "This week" else "Previous week"
    val listItems = articles.map { ArticleItemViewModel(it, showDetail) }
}

