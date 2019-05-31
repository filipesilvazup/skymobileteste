package br.com.filipe.domain.interactor

import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.repository.MovieRepository
import io.reactivex.Single

/**
 * Created by Murilo Moro on 31/01/19.
 */
class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository
) {

    fun getPopularMovies(): Single<List<Movie>> {
        return movieRepository.getPopularMovies()
    }
}