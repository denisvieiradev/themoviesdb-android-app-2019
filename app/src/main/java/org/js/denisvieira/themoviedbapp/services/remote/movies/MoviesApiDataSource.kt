package org.js.denisvieira.themoviedbapp.services.remote.movies

import io.reactivex.Observable
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.services.remote.movies.dto.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiDataSource {

    @GET("search/movie")
    fun searchMovies(
        @Query("query") queryText: String,
        @Query("page") page: Int
    ): Observable<Response<MoviesResponse>>

    @GET("movie/popular")
    fun upcomingMovies(
        @Query("page") page: Int
    ): Observable<Response<MoviesResponse>>

    @GET("movie/{id}")
    fun movie(@Path("id") id: Int): Observable<Response<Movie>>


}