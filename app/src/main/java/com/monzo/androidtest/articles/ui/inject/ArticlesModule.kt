package com.monzo.androidtest.articles.ui.inject

import android.content.Context
import com.monzo.androidtest.articles.api.GuardianServiceFactory
import com.monzo.androidtest.articles.data.repo.ArticlesRepository
import com.monzo.androidtest.articles.ui.list.ArticlesViewModel
import com.monzo.androidtest.articles.api.response.mapper.ArticleMapper
import com.monzo.androidtest.articles.ui.detail.ArticleDetailViewModel

class ArticlesModule {
    fun injectArticlesViewModel(context: Context) = ArticlesViewModel(ArticlesRepository(GuardianServiceFactory().createGuardianService(context), ArticleMapper()))
    fun injectArticleDetailViewModel(context: Context) = ArticleDetailViewModel(ArticlesRepository(GuardianServiceFactory().createGuardianService(context), ArticleMapper()))
}
