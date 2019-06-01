package br.com.filipe.presentation.movie

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import br.com.filipe.domain.model.Movie
import br.com.filipe.presentation.R
import br.com.filipe.presentation.databinding.ActivityMoviesBinding
import br.com.filipe.presentation.movie.detail.MovieDetailActivity
import br.com.filipe.presentation.ui.base.BaseActivity
import br.com.filipe.presentation.ui.extensions.observeNotNull
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.Serializable

class MovieListActivity : BaseActivity<ActivityMoviesBinding>(), MovieRecyclerAdapter.OnMovieClickListener {

    val viewModel by viewModel<MovieListViewModel>()

    private val movieRecyclerAdapter by inject<MovieRecyclerAdapter>()

    override fun getLayoutRes(): Int = R.layout.activity_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initRecyclerView()

        observeData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchPopularMovies()
    }

    private fun initRecyclerView() {
        movieRecyclerAdapter.listener = this
        binding.rvMovies.layoutManager = GridLayoutManager(this, 2)
        binding.rvMovies.adapter = movieRecyclerAdapter
    }

    private fun observeData() {
        viewModel.popularMovies.observeNotNull(this) {
            movieRecyclerAdapter.notifyChanged(it)
        }

        viewModel.error.observeNotNull(this) {
            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
        }

        viewModel.state.observeNotNull(this) {
            when (it) {
                is MovieListViewModel.State.GoToMovieDetail -> {
                    val intent = Intent(this, MovieDetailActivity::class.java)
                    intent.putExtra("movie", it.movie as Serializable)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onClickFavoriteMovie(movie: Movie) {
        viewModel.onClickMovie(movie)
    }
}