package br.com.filipe.data.repository

import br.com.filipe.data.local.db.movie.FavoriteMovieEntity
import br.com.filipe.data.local.db.movie.MovieDao
import br.com.filipe.data.remote.MovieService
import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(
    private val service: MovieService,
    private val dao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(): Single<List<Movie>> {
        return service.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .flattenAsObservable { it }
            .map {
                it.toMovie()
            }
            .toList()
            .doOnError { error ->
                error.message
            }
    }

}