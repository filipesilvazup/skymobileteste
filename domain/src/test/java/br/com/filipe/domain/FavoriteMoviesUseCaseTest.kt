package br.com.filipe.domain

import br.com.filipe.domain.model.Movie
import br.com.filipe.domain.repository.MovieRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Test

/**
 * Created by Murilo Moro on 04/02/19.
 */
class FavoriteMoviesUseCaseTest {

    private val repositoryMock = mock<MovieRepository>()

    private lateinit var useCase: FavoriteMoviesUseCase

    @Before
    fun `Setup test`() {
        useCase = FavoriteMoviesUseCase(repositoryMock)
    }

    @Test
    fun `Test updateFavoriteMovie() when is favorite and delete is called`() {
        //Prepare
        val mock = getMovieMock(true)

        //Action
        useCase.updateFavoriteMovie(mock)

        //Test
        verify(repositoryMock).deleteFavoriteMovie(mock.id)
    }

    @Test
    fun `Test updateFavoriteMovie() when is NOT favorite and save is called`() {
        //Prepare
        val mock = getMovieMock(false)

        //Action
        useCase.updateFavoriteMovie(mock)

        //Test
        verify(repositoryMock).saveFavoriteMovie(mock)
    }

    private fun getMovieMock(favorite: Boolean): Movie {
        return Movie(
            1,
            "title",
            5,
            "/image.jpg",
            favorite
        )
    }

}