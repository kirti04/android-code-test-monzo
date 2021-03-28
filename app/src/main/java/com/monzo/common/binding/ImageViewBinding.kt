package com.monzo.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter(value = ["thumbnail", "isRounded"], requireAll = false)
    fun loadImage(view: ImageView, thumbnail: String, isRounded: Boolean = false) {
        view.apply {
            if (isRounded) {
                Glide.with(context).load(thumbnail).apply(RequestOptions.circleCropTransform()).into(this)
            } else {
                Glide.with(context).load(thumbnail).into(this)
            }
        }
    }
}