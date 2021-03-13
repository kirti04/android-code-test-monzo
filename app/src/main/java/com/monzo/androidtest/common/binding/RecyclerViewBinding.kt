package com.monzo.androidtest.common.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.monzo.androidtest.common.recyclerview.RecyclerViewAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("items")
    fun setItems(view: RecyclerView, items: List<RecyclerViewAdapter.ItemViewModel>?) {
        if(items == null)
            return

        view.apply {
            if (adapter == null)
                adapter = RecyclerViewAdapter()

            layoutManager = LinearLayoutManager(this.context)
            (adapter as? RecyclerViewAdapter)?.update(items)
        }
    }
}

