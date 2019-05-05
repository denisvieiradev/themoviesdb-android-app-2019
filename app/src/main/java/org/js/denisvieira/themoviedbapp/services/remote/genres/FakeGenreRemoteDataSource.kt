package org.js.denisvieira.themoviedbapp.services.remote.genres

import io.reactivex.Observable
import org.js.denisvieira.themoviedbapp.services.remote.genres.dto.GenreResponse
import retrofit2.Response

class FakeGenreRemoteDataSource : GenresApiDataSource {

    override fun movieGenres(): Observable<Response<GenreResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}