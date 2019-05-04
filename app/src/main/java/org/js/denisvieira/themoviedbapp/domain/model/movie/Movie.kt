package org.js.denisvieira.themoviedbapp.domain.model.movie

import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import com.squareup.moshi.Json

data class Movie(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val genres: List<Genre>?,
    val popularity: Float?,
    val runtime: Int?,
    @Json(name = "vote_average") val voteAverage: Float?,
    @Json(name = "vote_count") val voteCount: Int?,
    @Json(name = "genre_ids") val genreIds: List<Int>?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "release_date") val releaseDate: String?
)