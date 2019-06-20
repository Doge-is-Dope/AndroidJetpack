package com.chunchiehliang.kotlin.demo.ui.movie

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.databinding.FragmentMovieBinding


class MovieFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMovieBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie, container, false
        )

        val viewModel: MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        binding.recyclerMovieList.layoutManager = LinearLayoutManager(context)
        binding.recyclerMovieList.adapter = MovieAdapter(viewModel.wordList)
        binding.recyclerMovieList.addItemDecoration(MarginItemDecoration((resources.getDimension(R.dimen.margin_normal)).toInt()))

        return binding.root
    }

    private inner class MarginItemDecoration(val margin: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)

            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = margin
            }
            outRect.bottom = margin
            outRect.left = margin
            outRect.right = margin
        }
    }
}
