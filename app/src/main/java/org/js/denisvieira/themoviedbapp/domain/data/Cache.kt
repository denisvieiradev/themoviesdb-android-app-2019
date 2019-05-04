package org.js.denisvieira.themoviedbapp.domain.data

import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre

object Cache {

    var genres = listOf<Genre>()

    fun cacheGenres(genres: List<Genre>) {
        Cache.genres = genres
    }


}