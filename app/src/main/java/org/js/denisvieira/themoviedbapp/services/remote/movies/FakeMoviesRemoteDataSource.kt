package org.js.denisvieira.themoviedbapp.services.remote.movies

import io.reactivex.Observable
import io.reactivex.Observable.create
import okhttp3.Headers
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.domain.model.genre.builder.GenreBuilder
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.domain.model.movie.builder.MovieBuilder
import org.js.denisvieira.themoviedbapp.services.remote.movies.dto.MoviesResponse
import retrofit2.Response
import java.util.*
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.mutableMapOf
import kotlin.collections.toList
import kotlin.concurrent.schedule
import kotlin.collections.mutableListOf

class FakeMoviesRemoteDataSource : MoviesApiDataSource {

    override fun searchMovies(queryText: String, page: Int): Observable<Response<MoviesResponse>> {
        return create {
            Timer().schedule(2000) {
                val upcomingMoviesResponse = getSearchableMovieResponse()
                val headerMap = getMappedHeader()

                it.onNext(Response.success(upcomingMoviesResponse, Headers.of(headerMap)))
            }
        }
    }

    override fun upcomingMovies(page: Int): Observable<Response<MoviesResponse>> {
        return create {
            Timer().schedule(2000) {
                val upcomingMoviesResponse = getUpcomingMoviesResponse()
                val headerMap = getMappedHeader()

                it.onNext(Response.success(upcomingMoviesResponse, Headers.of(headerMap)))
            }
        }
    }

    override fun movie(id: Int): Observable<Response<Movie>> {
        return create {
            Timer().schedule(2000) {
                val upcomingMoviesResponse = getFullMovieWithAllDetails()
                val headerMap = getMappedHeader()

                it.onNext(Response.success(upcomingMoviesResponse, Headers.of(headerMap)))
            }
        }
    }

    private fun getSearchableMovieResponse(): MoviesResponse {
        return MoviesResponse(
            1,
            mutableListOf(getMovies()[1]), 1, 1
        )
    }

    private fun getUpcomingMoviesResponse(): MoviesResponse {
        return MoviesResponse(
            1,
            getMovies(), 1, 4
        )
    }

    private fun getMovies(): List<Movie> {
        val movies: MutableList<Movie>? = mutableListOf(
            MovieBuilder().oneMovieResponse(1).build(),
            MovieBuilder().oneMovieResponse(2).build(),
            MovieBuilder().oneMovieResponse(3).build(),
            MovieBuilder().oneMovieResponse(4).build()
        )

        return movies!!.toList()
    }

    private fun getFullMovieWithAllDetails() : Movie {
        return MovieBuilder()
            .id(2)
            .title("Movie Detail 1")
            .voteAverage(9f)
            .voteCount(10)
            .runtime(90)
            .overview("Lorem Ipsum")
            .popularity(8f)
            .genres(getGenreList())
            .build()
    }

    private fun getGenreList(): MutableList<Genre> {
        return kotlin.collections.mutableListOf(
            GenreBuilder().name("Drama").build(),
            GenreBuilder().name("Action").build(),
            GenreBuilder().name("Terror").build()
        )
    }

    private fun getMappedHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "application/json" to "charset=utf-8"
        )
    }

}