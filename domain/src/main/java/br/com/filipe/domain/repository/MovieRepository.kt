package br.com.filipe.domain.repository

import br.com.filipe.domain.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {

    fun getPopularMovies(): Single<List<Movie>>
}