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
import com.google.android.material.snackbar.Snackbar


class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentMovieBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movie, container, false
        )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val adapter = MovieAdapter(MovieAdapter.MovieListener { movie ->
            viewModel.onMovieClicked(movie)
        })
        binding.recyclerMovieList.adapter = adapter
        binding.recyclerMovieList.addItemDecoration(MarginItemDecoration((resources.getDimension(R.dimen.margin_normal)).toInt()))

        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToMovieDetail.observe(this, Observer { movie ->
            movie?.let {
                this.findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movie)
                )
                viewModel.onMovieDetailNavigated()
            }
        })

        viewModel.status.observe(this, Observer {
            if (it == MovieViewModel.MovieApiStatus.ERROR) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.msg_connection_error),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        binding.fabFilter.setOnClickListener {
            Snackbar.make(binding.coordinatorLayout, "clicked", Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.fabFilter)
                .show()

//           this.findNavController().navigate(R.id.action_movieFragment_to_testFragment)
        }
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
