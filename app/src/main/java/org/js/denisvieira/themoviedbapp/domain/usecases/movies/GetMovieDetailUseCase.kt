package org.js.denisvieira.themoviedbapp.domain.usecases.movies

import org.js.denisvieira.themoviedbapp.domain.usecases.UseCase
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.movies.MoviesRemoteDataSource

class GetMovieDetailUseCase(private val mMoviesRemoteDataSource: MoviesRemoteDataSource) {

    fun getMovieDetail(id: Int, callback: UseCase.UseCaseCallback<Movie>) {
        mMoviesRemoteDataSource.getMovie(id, object : BaseRemoteDataSource.RemoteDataSourceCallback<Movie> {
            override fun onSuccess(response: Movie) {
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