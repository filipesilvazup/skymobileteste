package br.com.filipe.domain.interactor

import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.Movies
import br.com.filipe.domain.model.Paging
import br.com.filipe.domain.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieUseCase(
    private val movieRepository: MovieRepository
) {

    fun getPopularMovies(): Single<List<Movie>> {
        return (movieRepository.getPopularMovies())
    }

    fun fetchMovies(page: Int, pageSize: Int): Single<Paging<Movie>> {
        return movieRepository.fetchMovieNotifications(page, pageSize)
            .subscribeOn(Schedulers.io())
    }

    fun searchMovie(search:String, page: Int, pageSize: Int): Single<Paging<Movie>> {
        return movieRepository.searchMovie(search, page, pageSize)
            .subscribeOn(Schedulers.io())
    }
}