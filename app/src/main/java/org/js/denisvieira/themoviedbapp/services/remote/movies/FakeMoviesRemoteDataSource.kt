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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun upcomingMovies(page: Int): Observable<Response<MoviesResponse>> {
        return create {
            Timer().schedule(2000){
                val upcomingMoviesResponse = getUpcomingMoviesResponse()
                val headerMap = getMappedHeader()

                it.onNext(Response.success(upcomingMoviesResponse, Headers.of(headerMap)))
                    it.onError(Throwable(R.string.unauthorized.toString()))
            }
        }
    }

    override fun movie(id: Int): Observable<Response<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getUpcomingMoviesResponse(): MoviesResponse {
        return MoviesResponse(
            1,
            getMovies(), 2, 3
        )
    }

    private fun getMovies(): List<Movie> {
        val movies : MutableList<Movie>? = mutableListOf(
            MovieBuilder().oneMovieResponse().build(),
            MovieBuilder().oneMovieResponse().build()
        )

        return movies!!.toList()
    }

    private fun getMappedHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "application/json" to "charset=utf-8",
            "teste_header" to "y",
            "-1" to "zz"
        )
    }

}