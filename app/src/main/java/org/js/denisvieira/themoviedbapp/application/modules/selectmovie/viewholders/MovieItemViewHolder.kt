package org.js.denisvieira.themoviedbapp.application.modules.selectmovie.viewholders

import androidx.recyclerview.widget.RecyclerView
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.dto.MovieDto
import org.js.denisvieira.themoviedbapp.databinding.MovieItemViewHolderBinding

class MovieItemViewHolder(movieItemBinding: MovieItemViewHolderBinding) : RecyclerView.ViewHolder(movieItemBinding.root) {

    private val binding: MovieItemViewHolderBinding = movieItemBinding

    fun bind(movie: MovieDto) {
        binding.movie = movie
        binding.executePendingBindings()
    }
}