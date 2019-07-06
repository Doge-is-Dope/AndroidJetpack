package com.chunchiehliang.kotlin.demo2.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlin.demo2.R
import com.chunchiehliang.kotlin.demo2.databinding.ListItemMovieBinding
import com.chunchiehliang.kotlin.demo2.model.Movie

class MovieAdapter(val onClickListener: MovieListener) : ListAdapter<Movie, MovieAdapter.MovieItemViewHolder>(DiffCallback) {

    class MovieItemViewHolder(val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.movie = item
            binding.executePendingBindings()
        }
    }

    class MovieListener(val clickListener: (movie: Movie) -> Unit) {
        fun onClick(movie: Movie) = clickListener(movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        return MovieItemViewHolder(ListItemMovieBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener { onClickListener.onClick(movie) }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}




