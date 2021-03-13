package com.monzo.androidtest.articles.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

@Parcelize
data class Article(
        val id: String,
        val thumbnail: String,
        val sectionId: String,
        val sectionName: String,
        val published: DateTime,
        val title: String,
        val url: String,
        val body: String
):Parcelable
