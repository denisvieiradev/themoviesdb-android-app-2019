package org.js.denisvieira.themoviedbapp.application.injections

import org.js.denisvieira.themoviedbapp.InjectionApiDataSource
import org.js.denisvieira.themoviedbapp.services.remote.genres.GenresRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.genres.GenresRemoteDataSourceImpl
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesRemoteDataSourceImpl

object InjectionRemoteDataSource {

    fun provideMoviesRemoteDataSource(): MoviesRemoteDataSource {
        return MoviesRemoteDataSourceImpl(InjectionApiDataSource.provideMoviesApiDataSource())
    }

    fun provideGenresRemoteDataSource(): GenresRemoteDataSource {
        return GenresRemoteDataSourceImpl(InjectionApiDataSource.provideGenresApiDataSource())
    }


}