package com.monzo.articles.ui.inject

import android.content.Context
import com.monzo.articles.api.GuardianServiceFactory
import com.monzo.articles.data.repo.ArticlesRepository
import com.monzo.articles.ui.list.ArticlesViewModel
import com.monzo.articles.api.response.mapper.ArticleMapper
import com.monzo.articles.ui.detail.ArticleDetailViewModel

class ArticlesModule {
    fun injectArticlesViewModel(context: Context) = ArticlesViewModel(ArticlesRepository(GuardianServiceFactory().createGuardianService(context), ArticleMapper()))
    fun injectArticleDetailViewModel(context: Context) = ArticleDetailViewModel(ArticlesRepository(GuardianServiceFactory().createGuardianService(context), ArticleMapper()))
}
