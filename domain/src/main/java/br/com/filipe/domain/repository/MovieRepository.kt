package br.com.filipe.domain.repository

import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.model.Movies
import br.com.filipe.domain.model.Paging
import io.reactivex.Single

interface MovieRepository {

    fun getPopularMovies(): Single<List<Movie>>

    fun fetchMovieNotifications(page: Int, pageSize: Int): Single<Paging<Movie>>

    fun searchMovie(search:String, page: Int, pageSize: Int): Single<Paging<Movie>>
}