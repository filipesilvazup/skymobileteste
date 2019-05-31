package br.com.filipe.domain.interactor

import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single

class FavoriteMoviesUseCase(
    private val movieRepository: MovieRepository
) {

    fun updateFavoriteMovie(movie: Movie): Completable {
        return if (movie.favorite) {
            deleteFavoriteMovie(movie)
        } else {
            saveFavoriteMovie(movie)
        }
    }

    fun saveFavoriteMovie(movie: Movie): Completable {
        return movieRepository.saveFavoriteMovie(movie)
    }

    fun deleteFavoriteMovie(movie: Movie): Completable {
        return movieRepository.deleteFavoriteMovie(1)
    }

    fun getFavoriteMovies(): Single<List<Movie>> {
        return Single.just(arrayListOf()) //movieRepository.getFavoritesMovie()
    }
}