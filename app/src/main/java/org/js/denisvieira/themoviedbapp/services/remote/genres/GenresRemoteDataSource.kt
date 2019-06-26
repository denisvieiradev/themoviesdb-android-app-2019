package org.js.denisvieira.themoviedbapp.services.remote.genres

import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.services.BaseRemoteDataSource.RemoteDataSourceCallback

interface GenresRemoteDataSource {

    fun getMovieGenres(callback: RemoteDataSourceCallback<List<Genre>>)


}