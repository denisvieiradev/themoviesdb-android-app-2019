package org.js.denisvieira.themoviedbapp.domain.model.movie

import com.squareup.moshi.Json

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
