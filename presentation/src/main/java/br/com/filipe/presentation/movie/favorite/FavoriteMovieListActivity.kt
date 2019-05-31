package br.com.filipe.presentation.movie.favorite

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import br.com.filipe.domain.model.Movie
import br.com.filipe.presentation.R
import br.com.filipe.presentation.databinding.ActivityFavoriteMoviesBinding
import br.com.filipe.presentation.movie.MovieRecyclerAdapter
import br.com.filipe.presentation.ui.base.BaseActivity
import br.com.filipe.presentation.ui.extensions.observeNotNull
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteMovieListActivity : BaseActivity<ActivityFavoriteMoviesBinding>(),
    MovieRecyclerAdapter.OnMovieClickListener {

    val viewModel by viewModel<FavoriteMovieListViewModel>()

    private val movieRecyclerAdapter by inject<MovieRecyclerAdapter>()

    override fun getLayoutRes(): Int = R.layout.activity_favorite_movies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel

        initRecyclerView()

        observeData()

        viewModel.fetchFavoriteMovies()
    }

    private fun initRecyclerView() {
        movieRecyclerAdapter.listener = this
        binding.rvMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvMovies.adapter = movieRecyclerAdapter
    }

    private fun observeData() {
        viewModel.favoriteMovies.observeNotNull(this) {
            movieRecyclerAdapter.notifyChanged(it)
        }

        viewModel.error.observeNotNull(this) {
            //Do something
        }
    }

    override fun onClickFavoriteMovie(movie: Movie) {
        viewModel.onClickFavoriteMovie(movie)
    }
}