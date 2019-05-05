package org.js.denisvieira.themoviedbapp.domain.usecases.genres

import org.js.denisvieira.themoviedbapp.domain.usecases.UseCase
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource
import org.js.denisvieira.themoviedbapp.services.remote.genres.GenresRemoteDataSource

class GetMovieGenresUsecase(private val mGenresRemoteDataSource: GenresRemoteDataSource) {

    fun getMovieGenres(callback: UseCase.UseCaseCallback<List<Genre>>) {
        mGenresRemoteDataSource.getMovieGenres(object : BaseRemoteDataSource.RemoteDataSourceCallback<List<Genre>> {
            override fun onSuccess(response: List<Genre>) {
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