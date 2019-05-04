package org.js.denisvieira.themoviedbapp.services.remote.movies.dto

import com.squareup.moshi.Json
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
