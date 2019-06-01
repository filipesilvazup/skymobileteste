package br.com.filipe.domain.interactor

import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.repository.MovieRepository
import io.reactivex.Single

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository
) {

    fun getPopularMovies(): Single<List<Movie>> {
        return movieRepository.getPopularMovies()
    }
}