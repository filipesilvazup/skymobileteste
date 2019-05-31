package br.com.filipe.skymobiletest.di

import br.com.filipe.data.repository.MovieRepositoryImpl
import br.com.filipe.domain.interactor.FavoriteMoviesUseCase
import br.com.filipe.domain.interactor.GetPopularMoviesUseCase
import br.com.filipe.domain.repository.MovieRepository
import org.koin.dsl.module.module

val domainModule = module {

    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }

    factory { GetPopularMoviesUseCase(get()) }

    factory { FavoriteMoviesUseCase(get()) }
}