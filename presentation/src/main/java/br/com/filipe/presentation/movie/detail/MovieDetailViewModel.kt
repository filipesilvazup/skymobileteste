package br.com.filipe.presentation.movie.detail

import androidx.lifecycle.MutableLiveData
import br.com.filipe.domain.interactor.MovieUseCase
import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.MovieDetail
import br.com.filipe.presentation.ui.base.BaseViewModel

class MovieDetailViewModel(private val movieUseCase: MovieUseCase) : BaseViewModel() {

    val presentation = MutableLiveData<Presentation>()

    fun initPresentation() {
        presentation.value = getPresentation()
    }

    fun findMovieDetail(omdbId: String) {
        setLoading(true)
        subscribeSingle(
            observable = movieUseCase.getMovieDetailr(omdbId)
                .doOnSubscribe { showLoading.set(true) }
                .doFinally { showLoading.set(false) },
            success = {
                presentation.postValue(
                    getPresentation().copy(
                        title = it.title,
                        year = it.year,
                        imageUrl = it.imageUrl,
                        description = it.description,
                        posterPath = it.imageUrl,
                        loading = false
                    )
                )
            },
            error = {
                setLoading(false)
            }
        )
    }

    private fun setLoading(loading: Boolean) {
        presentation.postValue(
            getPresentation().copy(
                loading = loading
            )
        )
    }

    private fun getPresentation() = presentation.value
        ?: Presentation("", "", "", "", "", false)

    data class Presentation(
        val title: String,
        val year: String,
        val imageUrl: String,
        val description: String,
        val posterPath: String,
        val loading: Boolean
    )
}