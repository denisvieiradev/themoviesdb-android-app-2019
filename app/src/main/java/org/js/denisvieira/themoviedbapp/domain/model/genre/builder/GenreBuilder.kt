package org.js.denisvieira.themoviedbapp.domain.model.genre.builder

import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre

class GenreBuilder {

    private var id: Int = 0
    private var name: String = ""

    fun id(id: Int) = apply { this.id = id }
    fun name(title: String) = apply { this.name = title }


    fun build() = Genre(
        id,
        name
    )

    fun oneGenreResponse(id: Int): GenreBuilder {
        this.id = id
        this.name = "Genre $id"

        return this
    }
}