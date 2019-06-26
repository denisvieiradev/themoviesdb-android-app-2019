package org.js.denisvieira.themoviedbapp.services.remote.movies

import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource.RemoteDataSourceCallback

interface MoviesRemoteDataSource {

    fun searchMovies(queryText :String, page :Int, callback: RemoteDataSourceCallback<List<Movie>>)

    fun getUpcomingMovies(page : Int, callback: RemoteDataSourceCallback<List<Movie>>)

    fun getMovie(id: Int, callback: RemoteDataSourceCallback<Movie>)


}