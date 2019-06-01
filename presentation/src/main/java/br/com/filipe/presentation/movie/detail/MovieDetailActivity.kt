package br.com.filipe.presentation.movie.detail

import android.os.Bundle
import br.com.filipe.domain.model.Movie
import br.com.filipe.presentation.ui.base.BaseActivity
import br.com.filipe.presentation.R
import br.com.filipe.presentation.databinding.ActivityMovieDetailBinding
import br.com.filipe.presentation.ui.extensions.observeNotNull
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>() {

    private val viewModel by viewModel<MovieDetailViewModel>()

    override fun getLayoutRes() = R.layout.activity_movie_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val movie = intent?.extras?.get("movie") as Movie
        binding.vm = viewModel
        binding.toolbar.setNavigationOnClickListener { finish() }
        observeData()
        viewModel.setMovie(movie)

    }

    private fun observeData() {
        viewModel.presentation.observeNotNull(this) {
            binding.presentation = it
        }
    }

}