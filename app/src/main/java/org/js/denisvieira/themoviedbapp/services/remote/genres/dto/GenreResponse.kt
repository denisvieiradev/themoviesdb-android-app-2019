package org.js.denisvieira.themoviedbapp.services.remote.genres.dto

import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre

data class GenreResponse(val genres: List<Genre>)