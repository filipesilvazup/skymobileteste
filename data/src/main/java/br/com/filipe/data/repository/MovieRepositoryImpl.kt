package br.com.filipe.data.repository

import br.com.filipe.data.local.db.movie.MovieDao
import br.com.filipe.data.remote.MovieService
import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.Paging
import br.com.filipe.domain.repository.MovieRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class MovieRepositoryImpl(
    private val service: MovieService,
    private val dao: MovieDao
) : MovieRepository {

    override fun getPopularMovies(): Single<List<Movie>> {
        return service.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .flattenAsObservable { it.toListMovie() }
            .map {
                it
            }
            .toList()
            .doOnError {
                it.message
            }
    }

    override fun fetchMovieNotifications(page: Int, pageSize: Int): Single<Paging<Movie>> {
        return service.searchPagedMovie(page)
            .map { pagingDto ->
                Paging(
                    results = pagingDto.content.map { it.toMovie() },
                    pageCount = (pagingDto.totalElements ?: 0 / pageSize)
                )
            }
    }

    override fun searchMovie(search: String, page: Int, pageSize: Int): Single<Paging<Movie>> {
        return service.searchMovie(search, page)
            .map { pagingDto ->
                Paging(
                    results = pagingDto.content.map { it.toMovie() },
                    pageCount = (pagingDto.totalElements ?: 0 / pageSize)
                )
            }
    }
}