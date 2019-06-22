package com.chunchiehliang.kotlin.demo.ui

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.chunchiehliang.kotlin.demo.database.Movie
import com.chunchiehliang.kotlin.demo.widget.CustomSwipeRefreshLayout

/**
 * Sets the colors of the [CustomSwipeRefreshLayout] loading indicator.
 */
@BindingAdapter("swipeRefreshColors")
fun setSwipeRefreshColors(swipeRefreshLayout: CustomSwipeRefreshLayout, colorResIds: IntArray) {
    swipeRefreshLayout.setColorSchemeColors(*colorResIds)
}

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