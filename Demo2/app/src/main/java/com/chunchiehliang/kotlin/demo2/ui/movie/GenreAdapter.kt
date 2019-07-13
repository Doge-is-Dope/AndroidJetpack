package com.chunchiehliang.kotlin.demo2.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlin.demo2.R
import com.chunchiehliang.kotlin.demo2.databinding.ListItemGenreTagBinding
import com.chunchiehliang.kotlin.demo2.model.Genre

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    var genres = emptyList<Genre>()


    class GenreViewHolder(val binding: ListItemGenreTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Genre) {
            binding.genre = item
            binding.executePendingBindings()
        }
    }

    override fun getItemCount() = genres.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(genres[position])
    }


    companion object {
        fun from(parent: ViewGroup): GenreViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view: ListItemGenreTagBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.list_item_genre_tag, parent, false)
            return GenreViewHolder(view)
        }
    }
}




