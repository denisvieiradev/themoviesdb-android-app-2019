package org.js.denisvieira.themoviedbapp.application.modules.selectmovie.dto

import org.js.denisvieira.themoviedbapp.application.util.getMountedGenresString
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre

data class MovieItemDto(
    val id: Int?,
    val title: String?,
    val posterPath: String?,
    val genres: List<Genre>?,
    val releaseDateFormatted: String?,
    val mountedGenres: String = getMountedGenresString(genres)
)
