package org.js.denisvieira.themoviedbapp.application.modules.selectmovie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.dto.MovieDto
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.viewholders.LoadingIconItemViewHolder
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.viewholders.MovieItemViewHolder
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.constants.SelectMovieViewTypes.LOADING_FOOTER_VIEW_TYPE
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.constants.SelectMovieViewTypes.MOVIE_ITEM_VIEW_TYPE

class SelectMovieAdapter(var movies: MutableList<MovieDto>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mLoading = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MOVIE_ITEM_VIEW_TYPE.value -> {
                return MovieItemViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.movie_item_view_holder,
                        parent,
                        false))
            }
            LOADING_FOOTER_VIEW_TYPE.value -> {
                return LoadingIconItemViewHolder(DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.loading_icon_item_view_holder,
                        parent,
                        false))
            }
            else -> throw IllegalStateException("Invalid type, this type ot items $viewType can't be handled")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieItemViewHolder) {
            holder.bind(movies[position])
        } else if (holder is LoadingIconItemViewHolder) {
            holder.showProgressBar()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < movies.size || isNotLoading()) {
            MOVIE_ITEM_VIEW_TYPE.value
        } else {
            LOADING_FOOTER_VIEW_TYPE.value
        }
    }

    override fun getItemCount(): Int {
        return if (movies.size == 0 || isNotLoading()) {
            movies.size
        } else {
            movies.size + 1
        }
    }

    fun addAllMovies(movieDtos: List<MovieDto>?, itemCallback: MoviesAdapterItemCallback) {
        if (movieDtos != null) {
            for (movieDto in movieDtos) {
                add(movieDto, itemCallback)
            }
        }
    }

    private fun add(planningPlaceDto: MovieDto, itemCallback: MoviesAdapterItemCallback) {
        movies.add(planningPlaceDto)
        itemCallback.successAtPositionCallback(movies.size)
    }

    fun showLoadingFooter(itemCallback: MoviesAdapterItemCallback) {
        mLoading = true
        itemCallback.successAtPositionCallback(movies.size + 1)
    }

    fun hideLoadingFooter(itemCallback: MoviesAdapterItemCallback) {
        mLoading = false
        itemCallback.successAtPositionCallback(movies.size + 1)
    }

    private fun isNotLoading(): Boolean {
        return !mLoading
    }

    fun resetStatus() {
        movies.clear()
        movies = mutableListOf()
        notifyDataSetChanged()
    }

    interface MoviesAdapterItemCallback {

        fun successAtPositionCallback(position: Int)

    }

}
