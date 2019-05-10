package org.js.denisvieira.themoviedbapp

import org.js.denisvieira.themoviedbapp.services.remote.genres.FakeGenreRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.genres.GenresApiDataSource
import org.js.denisvieira.themoviedbapp.services.remote.movies.FakeMoviesRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesApiDataSource

object InjectionApiDataSource {

    fun provideMoviesApiDataSource() : MoviesApiDataSource {
        return FakeMoviesRemoteDataSource()
    }

    fun provideGenresApiDataSource(): GenresApiDataSource {
        return FakeGenreRemoteDataSource()
    }


}
