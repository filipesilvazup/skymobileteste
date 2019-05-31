package br.com.filipe.skymobiletest.di

import br.com.filipe.presentation.movie.MovieRecyclerAdapter
import br.com.filipe.presentation.movie.MovieListViewModel
import br.com.filipe.presentation.movie.favorite.FavoriteMovieListViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {

    viewModel { MovieListViewModel(get(), get()) }

    viewModel { FavoriteMovieListViewModel(get()) }

    factory { MovieRecyclerAdapter() }

}