package com.monzo.androidtest.articles.api.response.model

data class ApiArticleListResponse(val response: ApiArticleList)

data class ApiArticleList(val results: List<ApiArticle>)
