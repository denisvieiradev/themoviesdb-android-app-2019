package org.js.denisvieira.themoviedbapp.domain.model.movie.builders

import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie

class MovieBuilder {
    var id : Int? = null
    private var title : String = ""
    private var overview: String = ""
    private var genres: List<Genre>? = null
    private var genreIds: List<Int>? = null
    private var popularity: Float? = 0f
    private var runtime: Int? = 0
    private var posterPath: String = ""
    private var backdropPath: String = ""
    private var releaseDate: String = ""
    private var voteAverage: Float = 0f
    private var voteCount: Int = 0

    fun id(id: Int) = apply { this.id = id }
    fun title(title: String) = apply { this.title = title }
    fun overview(overview: String) = apply { this.overview = overview }
    fun genres(genres: List<Genre>) = apply { this.genres = genres }
    fun genreIds(genreIds: List<Int>) = apply { this.genreIds = genreIds }
    fun posterPath(posterPath: String) = apply { this.posterPath = posterPath }
    fun releaseDate(releaseDate: String) = apply { this.releaseDate = releaseDate }

    fun build() = Movie(
        id,
        title,
        overview,
        genres,
        popularity,
        runtime,
        voteAverage,
        voteCount,
        genreIds,
        posterPath,
        backdropPath,
        releaseDate
    )
}

fun createOneMovieBuilder(): MovieBuilder {
    return MovieBuilder()
            .id(32)
            .title("Teste Vieira")
            .releaseDate("2009-10-12")
}
