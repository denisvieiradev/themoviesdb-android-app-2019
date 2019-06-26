package org.js.denisvieira.themoviedbapp.application.modules.moviedetail.dto

import org.js.denisvieira.themoviedbapp.application.util.getMountedGenresString
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre

data class MovieDetailDto(
        val id: Int?,
        val title: String?,
        val posterPath: String?,
        val backdropPath: String?,
        val genres: List<Genre>?,
        val overview: String?,
        val voteCount: String?,
        val voteAverage: String?,
        val popularity: String?,
        val runtime: String?,
        val releaseDateFormatted: String?,
        val mountedGenres: String = getMountedGenresString(genres)
)


