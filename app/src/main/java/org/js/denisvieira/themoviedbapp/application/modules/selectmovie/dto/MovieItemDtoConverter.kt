package org.js.denisvieira.themoviedbapp.application.modules.selectmovie.dto

import android.content.Context
import org.js.denisvieira.themoviedbapp.application.util.extensions.formatToServerDateDefaults
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie

object MovieItemDtoConverter {

    fun convertEntitiesToDtos(list: List<Movie>, context: Context): List<MovieItemDto> {
        return list.map {
            convertEntityToDto(it, context)
        }
    }

    private fun convertEntityToDto(
        movie: Movie,
        context: Context
    ): MovieItemDto {
        return MovieItemDto(
            movie.id,
            movie.title,
            movie.posterPath,
            movie.genres,
            movie.getReleaseDate().formatToServerDateDefaults(context)
        )
    }


}