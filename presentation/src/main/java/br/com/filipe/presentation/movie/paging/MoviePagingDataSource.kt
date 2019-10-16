package br.com.filipe.presentation.movie.paging

import br.com.filipe.domain.interactor.MovieUseCase
import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.Paging
import br.com.filipe.presentation.ui.base.BasePagingDataSource
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class MoviePagingDataSource(
    private val movieUseCase: MovieUseCase,
    compositeDisposable: CompositeDisposable,
    listener: Listener? = null
) : BasePagingDataSource<Movie>(compositeDisposable, listener) {

    override fun fetchData(search: String, page: Int, pageSize: Int): Single<Paging<Movie>> {
        return movieUseCase.fetchMovies(page, pageSize).map { Paging(it.results, it.pageCount) }
    }

    fun searchMovie(search: String, page: Int, pageSize: Int): Single<Paging<Movie>> {
        return movieUseCase.searchMovie(search, page, pageSize)
            .map { Paging(it.results, it.pageCount) }
    }


}