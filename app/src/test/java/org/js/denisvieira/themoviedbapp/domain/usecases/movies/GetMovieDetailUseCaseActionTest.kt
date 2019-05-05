package org.js.denisvieira.themoviedbapp.domain.usecases.movies

import com.nhaarman.mockitokotlin2.*
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.domain.model.movie.builder.MovieBuilder
import org.js.denisvieira.themoviedbapp.domain.usecases.UseCase
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesRemoteDataSource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

/**
 * Unit tests for the implementation of {@link GetMovieDetailUseCase }
 */

@RunWith(Enclosed::class)
class GetMovieDetailUseCaseActionTest {

    internal abstract class DescribeGetMovieDetailUseCase {

        @Rule
        @JvmField
        val rule = MockitoJUnit.rule()!!

        @InjectMocks
        protected lateinit var getMovieDetailUseCase: GetMovieDetailUseCase

        @Mock
        protected lateinit var moviesRemoteDataSource: MoviesRemoteDataSource

        @Mock
        protected lateinit var useCaseCallback: UseCase.UseCaseCallback<Movie>

        @Before
        fun setUp() {
            getMovieDetailUseCase.getMovieDetail(1, useCaseCallback)
        }

    }

    internal class ContextOnSuccess : DescribeGetMovieDetailUseCase() {

        private val response = MovieBuilder()
            .oneMovieResponse()
            .build()

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<Movie>>().apply {
                verify(moviesRemoteDataSource, times(1)).getMovie(eq(1), capture())
                firstValue.onSuccess(response)
            }
        }

        @Test
        fun `It should callback auth on success`() {
            verify(useCaseCallback, times(1)).onSuccess(eq(response))
        }

        @Test
        fun `It should callback auth on success never interation with call back on error`() {
            verify(useCaseCallback, never()).onError(any())
        }

        @Test
        fun `It should callback auth on success never interation with call back is loading`() {
            verify(useCaseCallback, never()).isLoading(any())
        }
    }

    internal class ContextOnError : DescribeGetMovieDetailUseCase() {

        private val errorMessage = "error"

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<Movie>>().apply {
                verify(moviesRemoteDataSource, times(1)).getMovie(eq(1), capture())
                firstValue.onError(errorMessage)
            }
        }

        @Test
        fun `It should callback auth on error`() {
            verify(useCaseCallback).onError(eq(errorMessage))
        }

        @Test
        fun `It should callback auth on error never interation with call back on success`() {
            verify(useCaseCallback, never()).onSuccess(any())
        }

        @Test
        fun `It should callback auth on error never interation with call back is loading`() {
            verify(useCaseCallback, never()).isLoading(any())
        }

    }

    internal class ContextIsLoading : DescribeGetMovieDetailUseCase() {

        private val messageError = "error"

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<Movie>>().apply {
                verify(moviesRemoteDataSource, times(1)).getMovie(eq(1), capture())
                firstValue.isLoading(true)
            }
        }

        @Test
        fun `It should callback auth is loading`() {
            verify(useCaseCallback, times(1)).isLoading(true)
        }

        @Test
        fun `It should callback auth on error never interation with call back on success`() {
            verify(useCaseCallback, never()).onSuccess(any())
        }

        @Test
        fun `It should callback auth on error never interation with call back on error`() {
            verify(useCaseCallback, never()).onError(messageError)
        }

    }

}
