package org.js.denisvieira.themoviedbapp.application.injections

import org.js.denisvieira.themoviedbapp.services.ApiDataSource.Companion.createService
import org.js.denisvieira.themoviedbapp.services.remote.genres.GenresApiDataSource
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesApiDataSource

open class InjectionApiDataSourceMain {

    fun provideMoviesApiDataSource(): MoviesApiDataSource {
        return createService(MoviesApiDataSource::class.java)
    }

    fun provideGenresApiDataSource(): GenresApiDataSource {
        return createService(GenresApiDataSource::class.java)
    }

}