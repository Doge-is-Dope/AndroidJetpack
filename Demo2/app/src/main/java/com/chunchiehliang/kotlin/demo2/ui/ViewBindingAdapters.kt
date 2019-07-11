package com.chunchiehliang.kotlin.demo2.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chunchiehliang.kotlin.demo2.R
import com.chunchiehliang.kotlin.demo2.model.Movie
import com.chunchiehliang.kotlin.demo2.ui.movie.MovieAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}


@BindingAdapter("movieGenres")
fun TextView.movieGenres(genres: List<String>?) {
    genres?.let {
        text = genres.joinToString(" - ")
    }
}

//@BindingAdapter("movieGenres")
//fun ChipGroup.movieGenres(genres: List<String>?) {
//    //todo : bug
//    genres?.let {
//        for (genre in it) {
//            val tag = Chip(context)
//            tag.text = genre
//            addView(tag)
//        }
//    }
//}

private const val IMAGE_BASE_URL = "http://image.tmdb.org/t/p/"

@BindingAdapter("posterImageUrl")
fun bindPosterImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = (IMAGE_BASE_URL + "w185/" + imgUrl).toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions.placeholderOf(R.drawable.bg_image_placeholder))
            .into(imgView)
    }
}

@BindingAdapter("backDropImageUrl")
fun bindBackDropImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = (IMAGE_BASE_URL + "w500/" + imgUrl).toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions.placeholderOf(R.drawable.bg_image_placeholder))
            .into(imgView)
    }
}