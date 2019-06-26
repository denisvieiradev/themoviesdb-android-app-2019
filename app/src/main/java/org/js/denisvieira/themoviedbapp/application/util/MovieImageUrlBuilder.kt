package org.js.denisvieira.themoviedbapp.application.util

import org.js.denisvieira.themoviedbapp.BuildConfig

private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
const val API_KEY = BuildConfig.THE_MOVIE_DB_API_KEY

class MovieImageUrlBuilder {

    fun buildPosterUrl(posterPath: String): String {
        return "$POSTER_URL$posterPath?api_key=$API_KEY"
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return "$BACKDROP_URL$backdropPath?api_key=$API_KEY"
    }
}
