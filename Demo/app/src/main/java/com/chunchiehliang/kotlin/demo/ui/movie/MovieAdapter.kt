package com.chunchiehliang.kotlin.demo.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlin.demo.BR
import com.chunchiehliang.kotlin.demo.R

class MovieAdapter(val movieList: MutableList<MovieViewModel.Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieItemViewHolder>() {

    override fun getItemCount(): Int = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item_movie, parent, false
        )

        return MovieItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(movieList[position])
    }


    class MovieItemViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Any) {
            binding.setVariable(BR.movieViewModel, movie)
            binding.executePendingBindings()
        }
    }
}