package br.com.filipe.presentation.movie

import android.arch.lifecycle.MutableLiveData
import br.com.filipe.domain.exception.DefaultException
import br.com.filipe.domain.interactor.GetPopularMoviesUseCase
import br.com.filipe.domain.model.Movie
import br.com.filipe.presentation.ui.base.BaseViewModel
import br.com.filipe.presentation.utils.SingleLiveData

class MovieListViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase
) : BaseViewModel() {

    sealed class State {
       data class GoToMovieDetail(val movie: Movie) : State()
    }

    val popularMovies = MutableLiveData<List<Movie>>()

    val error = SingleLiveData<DefaultException>()

    val state = SingleLiveData<State>()

    fun fetchPopularMovies() {
        subscribeSingle(
            observable = getPopularMoviesUseCase.getPopularMovies()
                .doOnSubscribe { showLoading.set(true) }
                .doFinally { showLoading.set(false) },
            success = {
                popularMovies.postValue(it)
            },
            error = { error.postValue(it) }
        )
    }

    fun onClickMovie(movie: Movie) {
        state.value = State.GoToMovieDetail(movie)
    }

}