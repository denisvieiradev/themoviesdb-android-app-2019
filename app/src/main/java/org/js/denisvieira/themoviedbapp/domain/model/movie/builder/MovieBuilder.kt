package org.js.denisvieira.themoviedbapp.domain.model.movie.builder

import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.domain.model.genre.builder.GenreBuilder
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie

class MovieBuilder {

    private var id: Int? = 0
    private var title: String? = ""
    private var overview: String? = ""
    private var genres: List<Genre>? = arrayListOf()
    private var popularity: Float? = 0f
    private var runtime: Int? = 0
    private var voteAverage: Float? = 0f
    private var voteCount: Int? = 0
    private var genreIds: List<Int>? = arrayListOf()
    private var posterPath: String? = ""
    private var backdropPath: String? = ""
    private var releaseDate: String = ""

    fun id(id: Int) = apply { this.id = id }
    fun title(title: String) = apply { this.title = title }
    fun overview(overview: String) = apply { this.overview = overview }
    fun genres(genres: List<Genre>) = apply { this.genres = genres }
    fun popularity(popularity: Float) = apply { this.popularity = popularity }
    fun runtime(runtime: Int) = apply { this.runtime = runtime }
    fun voteAverage(voteAverage: Float) = apply { this.voteAverage = voteAverage }
    fun voteCount(voteCount: Int) = apply { this.voteCount = voteCount }
    fun genreIds(genreIds: List<Int>) = apply { this.genreIds = genreIds }
    fun posterPath(posterPath: String) = apply { this.posterPath = posterPath }
    fun backdropPath(backdropPath: String) = apply { this.backdropPath = backdropPath }
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

    fun oneMovieResponse(): MovieBuilder {
        this.id = 1
        this.title = "Movie Title"

        return this
    }
}