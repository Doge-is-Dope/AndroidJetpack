package com.chunchiehliang.kotlin.demo2.ui.movie

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.chunchiehliang.kotlin.demo2.R
import com.chunchiehliang.kotlin.demo2.databinding.FragmentMovieBinding
import com.chunchiehliang.kotlin.demo2.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by lazy {
        val activity = requireNotNull(this.activity) { "You can only access the viewModel after onActivityCreated()" }
        ViewModelProviders.of(this, MovieViewModel.Factory(activity.application))
            .get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMovieBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie, container, false
        )
        Timber.d("MovieFragment")
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = MovieAdapter(MovieAdapter.MovieListener { movie ->
            viewModel.onMovieClicked(movie.id)
        })
        binding.recyclerMovieList.adapter = adapter
        binding.recyclerMovieList.hasFixedSize()
//        binding.recyclerMovieList.addItemDecoration(MarginItemDecoration((resources.getDimension(R.dimen.margin_normal)).toInt()))

        viewModel.genreList.observe(viewLifecycleOwner, Observer {
            it?.let {
//                Timber.d("genre list:  $it")
            }
        })

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            it?.let {
                    adapter.submitList(it)
            }
        })

        viewModel.navigateToMovieDetail.observe(viewLifecycleOwner, Observer { movieId ->
            movieId?.let {
                this.findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movieId)
                )
                viewModel.onMovieDetailNavigated()
            }
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == MovieViewModel.MovieApiStatus.ERROR) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.msg_connection_error),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        return binding.root
    }

    /**
    Handle the RecyclerView item's margin
     */
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
