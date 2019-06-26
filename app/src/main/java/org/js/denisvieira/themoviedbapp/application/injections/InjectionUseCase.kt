package org.js.denisvieira.themoviedbapp.application.injections

import org.js.denisvieira.themoviedbapp.domain.usecases.genres.GetMovieGenresUsecase
import org.js.denisvieira.themoviedbapp.domain.usecases.movies.GetMovieDetailUseCase
import org.js.denisvieira.themoviedbapp.domain.usecases.movies.GetUpcomingMoviesUseCase
import org.js.denisvieira.themoviedbapp.domain.usecases.movies.SearchMoviesUseCase

object InjectionUseCase {

    private val mMoviesRemoteDataSource = InjectionRemoteDataSource.provideMoviesRemoteDataSource()
    private val mGenresRemoteDataSource = InjectionRemoteDataSource.provideGenresRemoteDataSource()

    fun provideGetPopularMovies(): GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCase(mMoviesRemoteDataSource)
    }

    fun provideGetMovieGenresUsecase(): GetMovieGenresUsecase {
        return GetMovieGenresUsecase(mGenresRemoteDataSource)
    }

    fun provideGetMovieDetailUsecase(): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(mMoviesRemoteDataSource)
    }

    fun provideSearchMoviesUseCase(): SearchMoviesUseCase {
        return SearchMoviesUseCase(mMoviesRemoteDataSource)
    }


}