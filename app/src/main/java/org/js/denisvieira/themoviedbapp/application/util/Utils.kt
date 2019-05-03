package org.js.denisvieira.themoviedbapp.application.util

import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre

fun getMountedGenresString(genres: List<Genre>?): String {
    return genres!!.joinToString(separator = ", ") { it.name }
}
