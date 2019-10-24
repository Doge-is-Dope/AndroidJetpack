package com.chunchiehliang.kotlin.demo2.ui.moviedetail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.chunchiehliang.kotlin.demo2.databinding.FragmentMovieDetailBinding
import com.chunchiehliang.kotlin.demo2.viewmodel.MovieDetailViewModel
import timber.log.Timber


class MovieDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application
        val binding = FragmentMovieDetailBinding.inflate(inflater)


        val movie = MovieDetailFragmentArgs.fromBundle(arguments!!).selectedMovieId

        val viewModelFactory = MovieDetailViewModel.Factory(movie, application)

        val viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        viewModel.selectedMovieId.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Timber.d("$it")
                binding.nestedScrollView.pageScroll(View.FOCUS_UP);
                binding.nestedScrollView.scrollTo(0, 0)
            }
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}
