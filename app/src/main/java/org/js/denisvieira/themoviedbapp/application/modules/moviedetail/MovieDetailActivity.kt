package org.js.denisvieira.themoviedbapp.application.modules.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.application.modules.moviedetail.dto.MovieDetailDtoConverter
import org.js.denisvieira.themoviedbapp.application.util.showAlertErrorByStatusCode
import org.js.denisvieira.themoviedbapp.databinding.MovieDetailActivityBinding
import org.js.denisvieira.themoviedbapp.domain.constants.AppKeys.KEY_MOVIE_ID

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mDetailViewModel: MovieDetailViewModel
    private lateinit var mBinding: MovieDetailActivityBinding
    private var mCurrentMovieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding                = DataBindingUtil.setContentView(this, R.layout.movie_detail_activity)
        mDetailViewModel        = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        mBinding.viewModel      = mDetailViewModel
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

    private fun extractBundleInformation(bundle: Bundle) {
        mCurrentMovieId = bundle.getInt(KEY_MOVIE_ID)
    }

    private fun startObservers() {
        startOnSuccessMainDataObserver()
        startOnErrorMainDataObserver()
    }

    private fun setupToolbar() {
        val toolbar = this.mBinding.movieDetailMainToolbar
        val appCompatActivity = this as AppCompatActivity?
        val actionBar: AppCompatActivity? = appCompatActivity

        actionBar?.setSupportActionBar(toolbar)
        actionBar?.supportActionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = getString(R.string.movie_detail_title)
    }

    private fun startOnSuccessMainDataObserver() {
        mDetailViewModel.onSuccessMainDataObserver.observe(this, Observer {
            mBinding.movieItem = MovieDetailDtoConverter.convertEntityToDto(it!!, this)
        })
    }

    private fun startOnErrorMainDataObserver() {
        mDetailViewModel.onErrorMainDataObserver.observe(this, Observer {
            if(it != null)
                showAlertErrorByStatusCode(it,this)
        })
    }


}
