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
 * Unit tests for the implementation of {@link SearchMoviesUseCase }
 */

@RunWith(Enclosed::class)
class SearchMoviesUseCaseActionTest {

    internal abstract class DescribeSearchMoviesUseCase {

        @Rule
        @JvmField
        val rule = MockitoJUnit.rule()!!

        @InjectMocks
        protected lateinit var searchMoviesUseCase: SearchMoviesUseCase

        @Mock
        protected lateinit var moviesRemoteDataSource: MoviesRemoteDataSource

        @Mock
        protected lateinit var useCaseCallback: UseCase.UseCaseCallback<List<Movie>>

        val queryText = "Thor"
        val pageNumber = 1

        @Before
        fun setUp() {
            searchMoviesUseCase.searchMovies("Thor", pageNumber, useCaseCallback)
        }

    }

    internal class ContextOnSuccess : DescribeSearchMoviesUseCase() {

        private val response = arrayListOf<Movie>(
            MovieBuilder()
                .oneMovieResponse()
                .build()
        )

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<List<Movie>>>().apply {
                verify(moviesRemoteDataSource, times(1)).searchMovies(eq(queryText), eq(pageNumber), capture())
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

    internal class ContextOnError : DescribeSearchMoviesUseCase() {

        private val errorMessage = "error"

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<List<Movie>>>().apply {
                verify(moviesRemoteDataSource, times(1)).searchMovies(eq(queryText), eq(1), capture())
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

    internal class Context_Is_Loading : DescribeSearchMoviesUseCase() {

        private val messageError = "error"

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<List<Movie>>>().apply {
                verify(moviesRemoteDataSource, times(1)).searchMovies(eq(queryText), eq(1), capture())
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
