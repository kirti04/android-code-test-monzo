package com.monzo.common.binding

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.BindingAdapter

object TextViewBinding {

    @RequiresApi(Build.VERSION_CODES.N)
    @JvmStatic
    @BindingAdapter("html")
    fun setTextFromHtml(view: TextView, html: String) {
        view.apply {
            text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        }
    }
}