package org.js.denisvieira.themoviedbapp.application.injections

import org.js.denisvieira.themoviedbapp.application.injections.InjectionApiDataSource.provideGenresApiDataSource
import org.js.denisvieira.themoviedbapp.application.injections.InjectionApiDataSource.provideMoviesApiDataSource
import org.js.denisvieira.themoviedbapp.services.remote.genres.GenresRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.genres.GenresRemoteDataSourceImpl
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesRemoteDataSourceImpl

object InjectionRemoteDataSource {

    fun provideMoviesRemoteDataSource(): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(provideMoviesApiDataSource())
    }

    fun provideGenresRemoteDataSource(): GenresRemoteDataSource {
        return GenresRemoteDataSourceImpl(provideGenresApiDataSource())
    }


}