package com.chunchiehliang.kotlin.demo.ui.moviedetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.chunchiehliang.kotlin.demo.R
import com.chunchiehliang.kotlin.demo.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMovieDetailBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_movie_detail,
                container, false)
        return binding.root
    }


}
