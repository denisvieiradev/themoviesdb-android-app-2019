package org.js.denisvieira.themoviedbapp.services.remote.movies

import io.reactivex.Observable
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.domain.model.movie.builder.MovieBuilder
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource.RemoteDataSourceCallback
import org.js.denisvieira.themoviedbapp.services.remote.movies.dto.MoviesResponse
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import retrofit2.Response

/**
 * Unit tests for the implementation of {@link MoviesRemoteDataSourceImpl }
 */

@RunWith(Enclosed::class)
class GetUpcomingMoviesActionTest {

    abstract class DescribeGetUpcomingMovies : MoviesRemoteDataSourceBaseTest() {

        @Mock
        lateinit var callback: RemoteDataSourceCallback<List<Movie>>

        val moviesResponse = getUpcomingMoviesResponse()

        private fun getUpcomingMoviesResponse(): MoviesResponse {
            val movies : MutableList<Movie>? = mutableListOf(
                MovieBuilder().oneMovieResponse().build(),
                MovieBuilder().oneMovieResponse().build()
            )
            return MoviesResponse(
                1,
                movies!!.toList(), 1, 3
            )
        }
    }

    class ContextOnSuccess : DescribeGetUpcomingMovies() {

        @Before
        fun setup() {

            val response : Response<MoviesResponse> = Response.success(moviesResponse)

            val observable = Observable.just(response)

            given(moviesApiDataSource.upcomingMovies(1)).willReturn(observable)

            moviesRemoteDataSourceImpl.getUpcomingMovies(1, callback)
        }



        @Test
        fun `it should call upcoming movies function on success callback`() {
            verify(callback, times(1)).onSuccess(moviesResponse.results)
        }

        @Test
        fun `it should call upcoming movies function on success and never interaction with error`() {
            verify(callback, never()).onError(com.nhaarman.mockitokotlin2.any())
        }

        @Test
        fun `it should call upcoming movies function on success and always have 2 interactions with is loading callback`() {
            verify(callback, times(2)).isLoading(com.nhaarman.mockitokotlin2.any())
        }

    }

    class ContextOnError : DescribeGetUpcomingMovies() {

        private val messageErrorExpected = "messageErrorExpected"
        private val messageErrorAtual = "messageErrorExpected"

        @Before
        fun setup() {
            given(moviesApiDataSource.upcomingMovies(1)).willReturn(
                Observable.error(
                    Exception(messageErrorAtual)
                )
            )

            moviesRemoteDataSourceImpl.getUpcomingMovies(1, callback)
        }

        @Test
        fun `it should call upcoming movies function on error callback`() {
            verify(callback, times(1)).onError(messageErrorExpected)
        }

        @Test
        fun `it should call upcoming movies function on success and never interaction with error`() {
            verify(callback, never()).onSuccess(com.nhaarman.mockitokotlin2.any())
        }

        @Test
        fun `it should call upcoming movies function on success and always have 2 interactions with is loading callback`() {
            verify(callback, times(2)).isLoading(com.nhaarman.mockitokotlin2.any())
        }

    }

}