package org.js.denisvieira.themoviedbapp.domain.usecases.movies

import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.domain.usecases.UseCase
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesRemoteDataSource

class GetUpcomingMoviesUseCase(private val mMoviesRemoteDataSource: MoviesRemoteDataSource) {

    fun getMovies(page: Int, callback: UseCase.UseCaseCallback<List<Movie>>) {
        mMoviesRemoteDataSource.getUpcomingMovies(page, object :
            BaseRemoteDataSource.RemoteDataSourceCallback<List<Movie>> {
            override fun onSuccess(response: List<Movie>) {
                callback.onSuccess(response)
            }

            override fun onError(errorMessage: String) {
                callback.onError(errorMessage)
            }

            override fun isLoading(isLoading: Boolean) {
                callback.isLoading(isLoading)
            }

        })
    }
}