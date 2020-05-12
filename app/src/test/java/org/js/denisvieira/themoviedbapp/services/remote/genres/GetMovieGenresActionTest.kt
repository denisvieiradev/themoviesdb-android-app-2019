package org.js.denisvieira.themoviedbapp.services.remote.genres

import io.reactivex.Observable
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.domain.model.genre.builder.GenreBuilder
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource.RemoteDataSourceCallback
import org.js.denisvieira.themoviedbapp.services.remote.genres.dto.GenreResponse
import org.junit.Before
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.Mock
import retrofit2.Response

/**
 * Unit tests for the implementation of {@link GenresRemoteDataSourceImpl }
 */

@RunWith(Enclosed::class)
class GetMovieGenresActionTest {

    abstract class DescribeGetMovieGenres: GenresRemoteDataSourceBaseTest() {

        @Mock
        lateinit var callback: RemoteDataSourceCallback<List<Genre>>

        val genresResponse = getMovieGenresRsponse()

        private fun getMovieGenresRsponse(): GenreResponse {
            val genres : MutableList<Genre>? = mutableListOf(
                GenreBuilder().oneGenreResponse(1).build(),
                GenreBuilder().oneGenreResponse(2).build()
            )

            return GenreResponse(
                genres!!.toList()
            )
        }

    }

    class ContextOnSuccess : DescribeGetMovieGenres() {

        @Before
        fun setup() {
            val response : Response<GenreResponse> = Response.success(genresResponse)
            val observable = Observable.just(response)

            given(genresApiDataSource.movieGenres()).willReturn(observable)

            genresRemoteDataSourceImpl.getMovieGenres(callback)
        }

        @Test
        fun `it should call get movie genres function on success callback`() {
            verify(callback, times(1)).onSuccess(genresResponse.genres)
        }

        @Test
        fun `it should call get movie genres function on success and never interaction with error`() {
            verify(callback, never()).onError(com.nhaarman.mockitokotlin2.any())
        }

        @Test
        fun `it should call get movie genres function on success and always have 2 interactions with is loading callback`() {
            verify(callback, times(2)).isLoading(com.nhaarman.mockitokotlin2.any())
        }

    }

    class ContextOnError : DescribeGetMovieGenres() {

        private val messageErrorExpected = "messageErrorExpected"
        private val messageErrorAtual = "messageErrorExpected"

        @Before
        fun setup() {
            given(genresApiDataSource.movieGenres()).willReturn(
                Observable.error(
                    Exception(messageErrorAtual)
                )
            )

            genresRemoteDataSourceImpl.getMovieGenres(callback)
        }

        @Test
        fun `it should call get movie genres function on error callback`() {
            verify(callback, times(1)).onError(messageErrorExpected)
        }

        @Test
        fun `it should call get movie genres function on success and never interaction with error`() {
            verify(callback, never()).onSuccess(com.nhaarman.mockitokotlin2.any())
        }

        @Test
        fun `it should call get movie genres function on success and always have 2 interactions with is loading callback`() {
            verify(callback, times(2)).isLoading(com.nhaarman.mockitokotlin2.any())
        }

    }

}