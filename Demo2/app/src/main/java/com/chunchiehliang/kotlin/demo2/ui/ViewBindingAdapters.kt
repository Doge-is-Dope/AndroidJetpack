package com.chunchiehliang.kotlin.demo2.ui

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chunchiehliang.kotlin.demo2.R
import com.chunchiehliang.kotlin.demo2.domain.Genre
import com.chunchiehliang.kotlin.demo2.domain.Movie
import com.chunchiehliang.kotlin.demo2.ui.movie.GenreAdapter
import com.chunchiehliang.kotlin.demo2.ui.movie.MovieAdapter
import com.chunchiehliang.kotlin.demo2.viewmodel.MovieViewModel.MovieApiStatus
import com.facebook.shimmer.ShimmerFrameLayout
import timber.log.Timber

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}

@BindingAdapter("movieApiStatus")
fun bindStatus(shimmerContainer: ShimmerFrameLayout, status: MovieApiStatus?) {
    when (status) {
        MovieApiStatus.LOADING -> {
            shimmerContainer.startShimmer()
        }
        MovieApiStatus.DONE -> {
            shimmerContainer.stopShimmer()
            shimmerContainer.visibility = View.GONE
        }

        MovieApiStatus.ERROR -> {
            shimmerContainer.stopShimmer()
            shimmerContainer.visibility = View.GONE
        }
    }
}

@BindingAdapter("shimmerStopIfNotNull")
fun shimmerStopIfNotNull(shimmerContainer: ShimmerFrameLayout, data: List<Movie>?) {
    if (data != null && data.isNotEmpty()) {
        shimmerContainer.stopShimmer()
        shimmerContainer.visibility = View.GONE
    }
}

@BindingAdapter("movieGenres")
fun TextView.movieGenres(genres: List<String>?) {
    genres?.let {
        text = genres.joinToString(" - ")
    }
}

@BindingAdapter("genreTags")
fun bindGenreList(recyclerView: RecyclerView, data: List<Genre>?) {
    recyclerView.adapter = (recyclerView.adapter as? GenreAdapter ?: GenreAdapter())
        .apply {
//            Timber.d("$data")
            genres = data ?: emptyList()
        }
}

@BindingAdapter("genreTagTint")
fun tagTint(textView: TextView, genreId: Int) {
    // Tint the colored dot
    (textView.compoundDrawablesRelative[0] as? GradientDrawable)?.setColor(
        tagTintOrDefault(genreId, textView.context)
    )
}

fun tagTintOrDefault(genreId: Int, context: Context): Int {
    return ContextCompat.getColor(
        context, when (genreId) {
            28 -> R.color.tag_color_1
            12 -> R.color.tag_color_2
            16 -> R.color.tag_color_3
            35 -> R.color.tag_color_4
            80 -> R.color.tag_color_5
            99 -> R.color.tag_color_6
            18 -> R.color.tag_color_7
            10751 -> R.color.tag_color_8
            14 -> R.color.tag_color_9
            36 -> R.color.tag_color_10
            27 -> R.color.tag_color_11
            10402 -> R.color.tag_color_12
            10749 -> R.color.tag_color_13
            9648 -> R.color.tag_color_14
            878 -> R.color.tag_color_15
            10770 -> R.color.tag_color_16
            53 -> R.color.tag_color_17
            10752 -> R.color.tag_color_18
            37 -> R.color.tag_color_19
            else -> R.color.tag_color_default
        }
    )
}

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