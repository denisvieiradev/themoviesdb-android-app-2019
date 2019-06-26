package org.js.denisvieira.themoviedbapp.application.modules.moviedetail.dto

import android.content.Context
import org.js.denisvieira.themoviedbapp.application.util.extensions.formatToServerDateDefaults
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie

object MovieDetailDtoConverter {

    fun convertEntityToDto(
        movie: Movie,
        context: Context
    ): MovieDetailDto {
        return MovieDetailDto(
             movie.id,
             movie.title,
             movie.posterPath,
             movie.backdropPath,
             movie.genres,
             movie.overview,
             movie.voteCount.toString(),
             movie.voteAverage.toString(),
             movie.popularity.toString(),
             movie.runtime.toString(),
             movie.getReleaseDate().formatToServerDateDefaults(context)
        )
    }


}