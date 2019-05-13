package org.js.denisvieira.themoviedbapp.services.remote.genres

import io.reactivex.Observable
import okhttp3.Headers
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.domain.model.genre.builder.GenreBuilder
import org.js.denisvieira.themoviedbapp.services.remote.genres.dto.GenreResponse
import retrofit2.Response
import java.util.*
import kotlin.concurrent.schedule

class FakeGenreRemoteDataSource : GenresApiDataSource {

    override fun movieGenres(): Observable<Response<GenreResponse>> {
        return Observable.create {
            Timer().schedule(2000) {
                val movieGenresResponse = getGenreResponse()
                val headerMap = getMappedHeader()

                it.onNext(Response.success(movieGenresResponse, Headers.of(headerMap)))
            }
        }
    }

    private fun getGenreResponse(): GenreResponse {
        return GenreResponse(mutableListOf(
           GenreBuilder().oneGenreResponse(1).build(),
           GenreBuilder().oneGenreResponse(2).build(),
           GenreBuilder().oneGenreResponse(3).build(),
           GenreBuilder().oneGenreResponse(4).build()
        ))
    }

    private fun getMappedHeader(): MutableMap<String, String> {
        return mutableMapOf(
            "application/json" to "charset=utf-8"
        )
    }

}