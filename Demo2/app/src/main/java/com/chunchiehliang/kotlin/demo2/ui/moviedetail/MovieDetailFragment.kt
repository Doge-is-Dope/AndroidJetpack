package com.chunchiehliang.kotlin.demo2.ui.moviedetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.chunchiehliang.kotlin.demo2.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentMovieDetailBinding.inflate(inflater)

        val movie = MovieDetailFragmentArgs.fromBundle(arguments!!).selectedMovie

        val viewModelFactory = DetailViewModelFactory(movie, application)

        binding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        binding.lifecycleOwner = this

        return binding.root
    }


}
