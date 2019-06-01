package br.com.filipe.skymobiletest.di

import br.com.filipe.presentation.movie.MovieRecyclerAdapter
import br.com.filipe.presentation.movie.MovieListViewModel
import br.com.filipe.presentation.movie.detail.MovieDetailViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val presentationModule = module {

    viewModel { MovieListViewModel(get()) }

    viewModel { MovieDetailViewModel() }

    factory { MovieRecyclerAdapter() }

}