package com.chunchiehliang.kotlin.demo2.ui

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.chunchiehliang.kotlin.demo2.model.Movie

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("movieTitle")
fun TextView.setMovieTitle(item: Movie?) {
    item?.let { text = it.title }
}

@BindingAdapter("movieReleaseDate")
fun TextView.setMovieReleaseDate(item: Movie?) {
    item?.let { text = it.releaseDate }
}