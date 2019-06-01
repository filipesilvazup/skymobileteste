package br.com.filipe.presentation.movie.detail

import android.arch.lifecycle.MutableLiveData
import br.com.filipe.domain.model.Movie
import br.com.filipe.presentation.ui.base.BaseViewModel

class MovieDetailViewModel : BaseViewModel() {

    val presentation = MutableLiveData<Presentation>()

    fun setMovie(movie: Movie) {
        presentation.value = getPresentation().copy(title = movie.title, year = movie.year, imageUrl = movie.imageUrl, description = movie.description, posterPath = movie.getImage())
    }

    private fun getPresentation() = presentation.value
        ?: Presentation("", "", "", "", "")

    data class Presentation(
        val title: String,
        val year: String,
        val imageUrl: String,
        val description: String,
        val posterPath: String
    )
}