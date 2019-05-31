package br.com.filipe.presentation.movie.favorite

import android.arch.lifecycle.MutableLiveData
import br.com.filipe.domain.exception.DefaultException
import br.com.filipe.domain.interactor.FavoriteMoviesUseCase
import br.com.filipe.domain.model.Movie
import br.com.filipe.presentation.ui.base.BaseViewModel
import br.com.filipe.presentation.utils.SingleLiveData


class FavoriteMovieListViewModel(
    private val favoriteMoviesUseCase: FavoriteMoviesUseCase
) : BaseViewModel() {

    val favoriteMovies = MutableLiveData<List<Movie>>()

    val error = SingleLiveData<DefaultException>()

    fun fetchFavoriteMovies() {
        subscribeSingle(
            observable = favoriteMoviesUseCase.getFavoriteMovies()
                .doOnSubscribe { showLoading.set(true) }
                .doFinally { showLoading.set(false) },
            success = { favoriteMovies.postValue(it) },
            error = { error.postValue(it) }
        )
    }

    fun onClickFavoriteMovie(movie: Movie) {
        subscribeCompletable(
            observable = favoriteMoviesUseCase.updateFavoriteMovie(movie),
            complete = { fetchFavoriteMovies() },
            error = { error.postValue(it) }
        )
    }

}