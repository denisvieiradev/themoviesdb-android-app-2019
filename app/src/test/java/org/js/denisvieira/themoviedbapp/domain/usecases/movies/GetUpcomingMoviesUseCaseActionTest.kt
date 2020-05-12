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
 * Unit tests for the implementation of {@link GetUpcomingMoviesUseCase }
 */

@RunWith(Enclosed::class)
class GetUpcomingMoviesUseCaseActionTest {

    internal abstract class DescribeGetMovieDetail {

        @Rule
        @JvmField
        val rule = MockitoJUnit.rule()!!

        @InjectMocks
        protected lateinit var getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase

        @Mock
        protected lateinit var moviesRemoteDataSource: MoviesRemoteDataSource

        @Mock
        protected lateinit var useCaseCallback: UseCase.UseCaseCallback<List<Movie>>

        @Before
        fun setUp() {
            getUpcomingMoviesUseCase.getMovies(1, useCaseCallback)
        }

    }

    internal class ContextOnSuccess : DescribeGetMovieDetail() {

        private val response = arrayListOf<Movie>(
            MovieBuilder()
                .oneMovieResponse(1)
                .build()
        )

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<List<Movie>>>().apply {
                verify(moviesRemoteDataSource, times(1)).getUpcomingMovies(eq(1), capture())
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

    internal class ContextOnError : DescribeGetMovieDetail() {

        private val errorMessage = "error"

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<List<Movie>>>().apply {
                verify(moviesRemoteDataSource, times(1)).getUpcomingMovies(eq(1), capture())
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

    internal class Context_Is_Loading : DescribeGetMovieDetail() {

        private val messageError = "error"

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<List<Movie>>>().apply {
                verify(moviesRemoteDataSource, times(1)).getUpcomingMovies(eq(1), capture())
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
