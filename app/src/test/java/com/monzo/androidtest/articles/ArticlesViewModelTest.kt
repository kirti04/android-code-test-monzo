package com.monzo.androidtest.articles

import com.monzo.androidtest.util.RxSchedulerRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.monzo.androidtest.articles.data.model.Article
import com.monzo.androidtest.articles.data.repo.ArticlesRepository
import com.monzo.androidtest.articles.data.state.ArticlesState
import com.monzo.androidtest.articles.ui.list.ArticlesViewModel
import io.reactivex.Single
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ArticlesViewModelTest {
    @Rule
    @JvmField
    val rxSchedulerRule = RxSchedulerRule()

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val repository = mock<ArticlesRepository>()

    private val viewModel get() = ArticlesViewModel(repository)

    @Test
    fun init() {
        val article = ArticleStubFactory.fakeArticle(title = "Article headline")
        whenever(repository.latestFintechArticles()).thenReturn(Single.just(listOf(article)))

        Assert.assertNotNull(viewModel.state.value)
        val state = viewModel.state.value!! as ArticlesState.LoadData
        Assert.assertEquals(state.refreshing, false)
        Assert.assertEquals(state.articles, listOf(article))
    }

    @Test
    fun onRefresh() {
        whenever(repository.latestFintechArticles()).thenReturn(Single.just(emptyList()))
        viewModel.onRefresh()

        Assert.assertNotNull(viewModel.state.value)
        val state = viewModel.state.value!! as ArticlesState.LoadData
        Assert.assertEquals(state.refreshing, false)
        Assert.assertEquals(state.articles, emptyList<Article>())
    }
}
