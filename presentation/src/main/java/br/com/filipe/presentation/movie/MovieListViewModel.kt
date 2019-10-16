package br.com.filipe.presentation.movie

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import br.com.filipe.domain.exception.DefaultException
import br.com.filipe.domain.interactor.MovieUseCase
import br.com.filipe.domain.model.Movie
import br.com.filipe.presentation.movie.paging.MoviePagingDataSource
import br.com.filipe.presentation.movie.paging.MoviePagingDataSourceFactory
import br.com.filipe.presentation.ui.base.BasePagingDataSource
import br.com.filipe.presentation.ui.base.BaseViewModel
import br.com.filipe.presentation.ui.extensions.percentage
import br.com.filipe.presentation.utils.SingleLiveData

class MovieListViewModel(
    private val movieUseCase: MovieUseCase
) : BaseViewModel(), BasePagingDataSource.Listener {
    companion object {
        private const val PAGE_SIZE = 20
        private const val PREFETCH_DISTANCE_PERCENT = 30
    }

    sealed class State {
        data class GoToMovieDetail(val movie: Movie) : State()
    }

    val popularMovies: LiveData<PagedList<Movie>> by lazy {
        initPaging()
    }

    val error = SingleLiveData<DefaultException>()

    val state = SingleLiveData<State>()


    private fun initPaging(): LiveData<PagedList<Movie>> {
        val factory = MoviePagingDataSourceFactory(
            movieUseCase,
            compositeDisposable,
            this
        )

        val config = PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPrefetchDistance(PAGE_SIZE.percentage(PREFETCH_DISTANCE_PERCENT))
            .setEnablePlaceholders(false)
            .build()

        return LivePagedListBuilder(factory, config).build()
    }

    private fun getDataSource(): MoviePagingDataSource? {
        return popularMovies.value?.let { it.dataSource as MoviePagingDataSource }
    }

    fun fetchPopularMovies() {
//        subscribeSingle(
//            observable = movieUseCase.getPopularMovies()
//                .doOnSubscribe { showLoading.set(true) }
//                .doFinally { showLoading.set(false) },
//            success = {
//                popularMovies.postValue(it)
//            },
//            error = { error.postValue(it) }
//        )
    }

    fun onClickMovie(movie: Movie) {
        state.value = State.GoToMovieDetail(movie)
    }

    fun searchMovies(search: String) {
        subscribeSingle(
            observable = movieUseCase.searchMovie(search, 1, 10)
                .doOnSubscribe { showLoading.set(true) }
                .doFinally { showLoading.set(false) },
            success = {

            },
            error = { error.postValue(it) }
        )
    }


    override fun initialEmpty(empty: Boolean) {
    }

    override fun initialLoading(show: Boolean) {
        showLoading.set(show)
    }

    override fun initialLoadError(error: Throwable) {
        showLoading.set(false)
    }

    override fun pagingLoading(show: Boolean) {
    }

    override fun pagingLoadError(error: Throwable) {
        getDataSource()?.retryErrorPage()
    }

}