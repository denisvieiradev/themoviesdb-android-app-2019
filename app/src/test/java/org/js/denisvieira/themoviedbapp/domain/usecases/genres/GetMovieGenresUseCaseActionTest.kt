package org.js.denisvieira.themoviedbapp.domain.usecases.genres

import com.nhaarman.mockitokotlin2.*
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.domain.model.genre.builder.GenreBuilder
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.domain.model.movie.builder.MovieBuilder
import org.js.denisvieira.themoviedbapp.domain.usecases.UseCase
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.genres.GenresRemoteDataSource
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

/**
 * Unit tests for the implementation of {@link GetMovieGenresUseCase }
 */

@RunWith(Enclosed::class)
class GetMovieGenresUseCaseActionTest {

    internal abstract class DescribeGetMovieGenresUseCase {

        @Rule
        @JvmField
        val rule = MockitoJUnit.rule()!!

        @InjectMocks
        protected lateinit var getMovieGenresUsecase: GetMovieGenresUsecase

        @Mock
        protected lateinit var genresRemoteDataSource: GenresRemoteDataSource

        @Mock
        protected lateinit var useCaseCallback: UseCase.UseCaseCallback<List<Genre>>

        @Before
        fun setUp() {
            getMovieGenresUsecase.getMovieGenres(useCaseCallback)
        }

    }

    internal class ContextOnSuccess : DescribeGetMovieGenresUseCase() {

        private val response = arrayListOf<Genre>(
            GenreBuilder()
                .oneGenreResponse()
                .build()
        )

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<List<Genre>>>().apply {
                verify(genresRemoteDataSource, times(1)).getMovieGenres(capture())
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

    internal class ContextOnError : DescribeGetMovieGenresUseCase() {

        private val errorMessage = "error"

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<List<Genre>>>().apply {
                verify(genresRemoteDataSource, times(1)).getMovieGenres(capture())
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

    internal class ContextIsLoading : DescribeGetMovieGenresUseCase() {

        private val messageError = "error"

        @Before
        fun setup() {
            argumentCaptor<BaseRemoteDataSource.RemoteDataSourceCallback<List<Genre>>>().apply {
                verify(genresRemoteDataSource, times(1)).getMovieGenres(capture())
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
