package org.js.denisvieira.themoviedbapp.services.remote.genres

import io.reactivex.Observable
import org.js.denisvieira.themoviedbapp.services.remote.genres.dto.GenreResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenresApiDataSource {

    @GET("genre/movie/list")
    fun movieGenres(): Observable<Response<GenreResponse>>

}