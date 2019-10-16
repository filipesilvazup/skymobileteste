package br.com.filipe.presentation.movie.paging

import androidx.paging.DataSource
import br.com.filipe.domain.interactor.MovieUseCase
import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.Movies
import br.com.filipe.presentation.ui.base.BasePagingDataSource
import io.reactivex.disposables.CompositeDisposable

class MoviePagingDataSourceFactory (
    private val movieUseCase: MovieUseCase,
    private val compositeDisposable: CompositeDisposable,
    private val listener: BasePagingDataSource.Listener? = null
) : DataSource.Factory<Int, Movie>() {

    override fun create() = MoviePagingDataSource(
        movieUseCase,
        compositeDisposable,
        listener
    )
}