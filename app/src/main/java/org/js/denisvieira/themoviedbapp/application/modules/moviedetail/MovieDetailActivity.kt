package org.js.denisvieira.themoviedbapp.application.modules.moviedetail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.application.modules.moviedetail.dto.MovieDetailDto
import org.js.denisvieira.themoviedbapp.application.util.extensions.formatToServerDateDefaults
import org.js.denisvieira.themoviedbapp.application.util.showAlertErrorByStatusCode
import org.js.denisvieira.themoviedbapp.domain.constants.AppKeys.KEY_MOVIE_ID
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
import java.text.SimpleDateFormat
import java.util.*


class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mDetailViewModel: MovieDetailViewModel
    private lateinit var mBinding: org.js.denisvieira.themoviedbapp.databinding.MovieDetailActivityBinding
    private var mCurrentMovieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.movie_detail_activity)
        mDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        mBinding.viewModel = mDetailViewModel
        mBinding.lifecycleOwner = this

        extractBundleInformation(intent.extras!!)

        mDetailViewModel.loadMovieDetail(mCurrentMovieId!!)

        startObservers()
        setupToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun startObservers() {
        startOnSuccessMainDataObserver()
        startOnErrorMainDataObserver()
    }

    private fun setupToolbar() {
        val toolbar = this.mBinding.movieDetailToolbar
        val appCompatActivity = this as AppCompatActivity?
        val actionBar: AppCompatActivity? = appCompatActivity

        actionBar?.setSupportActionBar(toolbar)
        actionBar?.supportActionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = getString(R.string.movie_detail_title)

    }

    private fun extractBundleInformation(bundle: Bundle) {
        mCurrentMovieId = bundle.getInt(KEY_MOVIE_ID)
    }

    private fun startOnSuccessMainDataObserver() {
        mDetailViewModel.onSuccessMainDataObserver.observe(this, Observer {
            mBinding.movie = mapMovieDetailDto(it!!)
        })
    }

    private fun startOnErrorMainDataObserver() {
        mDetailViewModel.onErrorMainDataObserver.observe(this, Observer {
            if(it != null)
                showAlertErrorByStatusCode(it,this)
        })
    }

    private fun mapMovieDetailDto(movie: Movie): MovieDetailDto {
        return MovieDetailDto(movie.id, movie.title, movie.posterPath, movie.backdropPath,
                movie.genres,
                movie.overview, movie.voteCount.toString(), movie.voteAverage.toString(),
                movie.popularity.toString(), movie.runtime.toString(), formatReleaseDate(movie.releaseDate))
    }

    @SuppressLint("SimpleDateFormat")
    fun formatReleaseDate(dateString: String?): String? {
        val formatter = SimpleDateFormat("yyyy-MM-dd")

        try {
            val date : Date = formatter.parse(dateString) as Date
            return date.formatToServerDateDefaults()
        } catch (e : Exception) {

        }

        return getString(R.string.invalid_date)
    }
}
