package com.monzo.articles.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.monzo.HeadlinesApp
import com.monzo.androidtest.R
import com.monzo.articles.data.model.Article
import com.monzo.common.recyclerview.RecyclerViewAdapter
import com.monzo.androidtest.databinding.ActivityArticletDetailBinding

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: ArticleDetailViewModel
    private lateinit var binding: ActivityArticletDetailBinding
    private val recyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_articlet_detail)
        setSupportActionBar(binding.articleDetailToolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        viewModel = HeadlinesApp.from(applicationContext).injectArticleDetailViewModel(this)

        initView()
        initObserver()

        val article = intent.getParcelableExtra("args_article") as Article?
        viewModel.setArgs(article)
    }

    private fun initView() {
        if (binding.articleDetailList.adapter == null)
            binding.articleDetailList.adapter = recyclerViewAdapter

        binding.articleDetailList.layoutManager = LinearLayoutManager(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> false
        }
    }

    private fun initObserver() {
        viewModel.state.observe(this, Observer { state ->
            updateRecyclerView(state.article)
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    private fun updateRecyclerView(article: Article?) {
        val items = viewModel.getListItems(article)
        (binding.articleDetailList.adapter as? RecyclerViewAdapter)?.update(items)
    }
}
