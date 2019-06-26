package org.js.denisvieira.themoviedbapp.application.modules.selectmovie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.dto.MovieItemDto
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.viewholders.LoadingIconItemViewHolder
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.viewholders.MovieItemViewHolder
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.constants.SelectMovieViewTypes.LOADING_FOOTER_VIEW_TYPE
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.constants.SelectMovieViewTypes.MOVIE_ITEM_VIEW_TYPE

class SelectMovieAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var movieItems: MutableList<MovieItemDto>
    private var mLoading = false

    init {
        movieItems = arrayListOf()
    }

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
            holder.bind(movieItems[position])
        } else if (holder is LoadingIconItemViewHolder) {
            holder.showProgressBar()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < movieItems.size || isNotLoading()) {
            MOVIE_ITEM_VIEW_TYPE.value
        } else {
            LOADING_FOOTER_VIEW_TYPE.value
        }
    }

    override fun getItemCount(): Int {
        return if (movieItems.size == 0 || isNotLoading()) {
            movieItems.size
        } else {
            movieItems.size + 1
        }
    }

    fun addAllMovies(movieItemsDtos: List<MovieItemDto>?, itemCallback: MoviesAdapterItemCallback) {
        if (movieItemsDtos != null) {
            for (movieDto in movieItemsDtos) {
                add(movieDto, itemCallback)
            }
        }
    }

    private fun add(movieItemDto: MovieItemDto, itemCallback: MoviesAdapterItemCallback) {
        movieItems.add(movieItemDto)
        itemCallback.successAtPositionCallback(movieItems.size)
    }

    fun showLoadingFooter(itemCallback: MoviesAdapterItemCallback) {
        mLoading = true
        itemCallback.successAtPositionCallback(movieItems.size + 1)
    }

    fun hideLoadingFooter(itemCallback: MoviesAdapterItemCallback) {
        mLoading = false
        itemCallback.successAtPositionCallback(movieItems.size + 1)
    }

    private fun isNotLoading(): Boolean {
        return !mLoading
    }

    fun resetStatus() {
        movieItems.clear()
        movieItems = mutableListOf()
        notifyDataSetChanged()
    }

    interface MoviesAdapterItemCallback {

        fun successAtPositionCallback(position: Int)

    }

}
