package com.monzo.androidtest.articles.api.response.mapper

import com.monzo.androidtest.articles.api.response.model.ApiArticle
import com.monzo.androidtest.articles.api.response.model.ApiArticleListResponse
import com.monzo.androidtest.articles.data.model.Article
import org.joda.time.DateTime

class ArticleMapper {

    fun mapList(input: ApiArticleListResponse): List<Article> = input.response.results.map {
        mapArticle(it)
    }

    fun mapArticle(input: ApiArticle): Article = Article(
            id = input.id ?: "",
            thumbnail = input.fields?.thumbnail ?: "",
            sectionId = input.sectionId ?: "",
            sectionName = input.sectionName ?: "",
            published = DateTime(input.webPublicationDate),
            title = input.fields?.headline ?: "",
            url = input.apiUrl ?: "",
            body = input.fields?.body ?: ""
    )
}
