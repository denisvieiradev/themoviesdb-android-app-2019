package org.js.denisvieira.themoviedbapp.services.remote.movies

import io.reactivex.Observable
import io.reactivex.Observable.create
import okhttp3.Headers
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.domain.model.movie.builder.MovieBuilder
import org.js.denisvieira.themoviedbapp.services.remote.movies.dto.MoviesResponse
import retrofit2.Response
import java.util.*
import kotlin.concurrent.schedule

class FakeMoviesRemoteDataSource : MoviesApiDataSource {

    override fun searchMovies(queryText: String, page: Int): Observable<Response<MoviesResponse>> {
        return create {
            Timer().schedule(2000){
                val upcomingMoviesResponse = getSearchableMovieResponse()
                val headerMap = getMappedHeader()

                it.onNext(Response.success(upcomingMoviesResponse, Headers.of(headerMap)))
            }
        }
    }

    override fun upcomingMovies(page: Int): Observable<Response<MoviesResponse>> {
        return create {
            Timer().schedule(2000){
                val upcomingMoviesResponse = getUpcomingMoviesResponse()
                val headerMap = getMappedHeader()

                it.onNext(Response.success(upcomingMoviesResponse, Headers.of(headerMap)))
            }
        }
    }

    override fun movie(id: Int): Observable<Response<Movie>> {
        return create {
            Timer().schedule(2000){
                val upcomingMoviesResponse = getMovies()[0]
                val headerMap = getMappedHeader()

                it.onNext(Response.success(upcomingMoviesResponse, Headers.of(headerMap)))
            }
        }
    }

    private fun getSearchableMovieResponse(): MoviesResponse {
        return MoviesResponse(
            1,
            mutableListOf(getMovies()[1]),1,1
        )
    }

    private fun getUpcomingMoviesResponse(): MoviesResponse {
        return MoviesResponse(
            1,
            getMovies(), 1, 4
        )
    }

    private fun getMovies(): List<Movie> {
        val movies : MutableList<Movie>? = mutableListOf(
            MovieBuilder().oneMovieResponse(1).build(),
            MovieBuilder().oneMovieResponse(2).build(),
            MovieBuilder().oneMovieResponse(3).build(),
            MovieBuilder().oneMovieResponse(4).build()
        )

        return movies!!.toList()
    }

    private fun getMappedHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "application/json" to "charset=utf-8"
        )
    }

}