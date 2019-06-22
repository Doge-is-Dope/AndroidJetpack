package com.chunchiehliang.kotlin.demo.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.database.Movie
import com.chunchiehliang.kotlin.demo.databinding.ListItemMovieBinding

class MovieAdapter(val clickListener: MovieListener) : ListAdapter<Movie, MovieAdapter.MovieItemViewHolder>(MovieDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return from(parent)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }


    class MovieItemViewHolder(val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie, clickListener: MovieListener
        ) {
            binding.movie = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object {
        private fun from(parent: ViewGroup): MovieItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ListItemMovieBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.list_item_movie, parent, false
            )
            return MovieItemViewHolder(binding)
        }
    }
}


class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

class MovieListener(val clickListener: (movieId: Long) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie.id)
}