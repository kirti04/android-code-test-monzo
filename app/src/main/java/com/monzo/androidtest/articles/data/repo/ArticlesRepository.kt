package com.monzo.androidtest.articles.data.repo

import com.monzo.androidtest.articles.api.GuardianService
import com.monzo.androidtest.articles.api.response.model.ApiArticle
import com.monzo.androidtest.articles.data.model.Article
import com.monzo.androidtest.articles.api.response.mapper.ArticleMapper
import io.reactivex.Single

class ArticlesRepository(
        private val guardianService: GuardianService,
        private val articleMapper: ArticleMapper
) {
    fun latestFintechArticles(): Single<List<Article>> {
        return guardianService.searchArticles("fintech,brexit")
                .map { articleMapper.mapList(it) }
    }

    fun getArticle(articleUrl: String): Single<Article> {
        return guardianService.getArticle(articleUrl, "main,body,headline,thumbnail").map {
            articleMapper.mapArticle(it)
        }
    }
}
