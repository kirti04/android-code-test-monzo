package com.monzo.androidtest.articles.ui.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.monzo.androidtest.HeadlinesApp
import com.monzo.androidtest.R
import com.monzo.androidtest.articles.data.model.Article
import com.monzo.androidtest.articles.data.state.ArticlesState
import com.monzo.androidtest.articles.ui.detail.ArticleDetailActivity
import com.monzo.androidtest.common.recyclerview.RecyclerViewAdapter
import com.monzo.androidtest.databinding.ActivityArticleListBinding

class ArticlesActivity : AppCompatActivity() {
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var binding: ActivityArticleListBinding
    private val articleListAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_article_list)
        setSupportActionBar(binding.articleListToolbar)

        viewModel = HeadlinesApp.from(applicationContext).injectArticlesViewModel(this)

        initView()
        initObserver()
    }

    private fun initView() {
        if (binding.articleList.adapter == null)
            binding.articleList.adapter = articleListAdapter

        binding.articleList.layoutManager = LinearLayoutManager(this)
        binding.articleListSwipeToRefresh.setOnRefreshListener { viewModel.onRefresh() }
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is ArticlesState.LoadData -> {
                    binding.articleListSwipeToRefresh.isRefreshing = state.refreshing
                    updateRecyclerView(state.articles)
                }
                is ArticlesState.ShowDetail -> {
                    val intent = Intent(this, ArticleDetailActivity::class.java)
                    intent.putExtra("args_article", state.article)
                    startActivity(intent)
                }
            }
        })
    }

    private fun updateRecyclerView(articles: List<Article>) {
        val listItems = viewModel.getListItems(articles)
        (binding.articleList.adapter as? RecyclerViewAdapter)?.update(listItems)
    }
}
