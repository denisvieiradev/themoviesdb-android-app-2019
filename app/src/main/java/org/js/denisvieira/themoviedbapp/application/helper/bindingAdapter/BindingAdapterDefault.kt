package org.js.denisvieira.themoviedbapp.application.helper.bindingAdapter

import android.net.Uri
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.application.util.MovieImageUrlBuilder

@BindingAdapter( "imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (hasNoUlr(imageUrl)) return

    try {
        val uri = Uri.parse(imageUrl)

        Glide.with(view.context)
                .load(uri)
                .apply(RequestOptions().placeholder(R.color.lightGray))
                .into(view)

    } catch (e: Exception) {
        println("error on image")
    }
}

@BindingAdapter( "posterImageUrl")
fun loadPosterImage(view: ImageView, posterImageUrl: String?) {
    if (hasNoUlr(posterImageUrl)) return

    try {
        val movieImageUrlBuilder = MovieImageUrlBuilder()
        val correctMovieImageUrl = posterImageUrl.let { movieImageUrlBuilder.buildPosterUrl(it!!) }

        loadImage(view, correctMovieImageUrl)

    } catch (e: Exception) {
        println("error on image")
    }
}

@BindingAdapter( "backdropImageUrl")
fun loadBackdropImage(view: ImageView, backdropImageUrl: String?) {
    if (hasNoUlr(backdropImageUrl)) return

    try {
        val movieImageUrlBuilder = MovieImageUrlBuilder()
        val correctMovieImageUrl = backdropImageUrl.let { movieImageUrlBuilder.buildBackdropUrl(it!!) }

        loadImage(view, correctMovieImageUrl)
    } catch (e: Exception) {
        println("error on image")
    }

}

private fun hasNoUlr(url: String?): Boolean {
    return url == null || url.isEmpty()
}

@BindingAdapter("visibleOrGone")
fun setVisibleOrGone(view: View, show: Boolean) {
    view.visibility = if (show) VISIBLE else GONE
}

