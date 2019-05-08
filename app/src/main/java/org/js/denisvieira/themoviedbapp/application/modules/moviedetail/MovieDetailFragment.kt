//package org.js.denisvieira.themoviedbapp.application.modules.moviedetail
//
//import android.annotation.SuppressLint
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.appcompat.app.AppCompatActivity
//import androidx.databinding.DataBindingUtil
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import androidx.navigation.fragment.findNavController
//import androidx.navigation.fragment.navArgs
//import org.js.denisvieira.themoviedbapp.R
//import org.js.denisvieira.themoviedbapp.application.modules.moviedetail.dto.MovieDetailDto
//import org.js.denisvieira.themoviedbapp.application.util.extensions.formatToServerDateDefaults
//import org.js.denisvieira.themoviedbapp.application.util.showAlertErrorByStatusCode
//import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
//import java.text.SimpleDateFormat
//import java.util.*
//
//
//class MovieDetailFragment : Fragment() {
//
//    private lateinit var mDetailViewModel: MovieDetailViewModel
//    private lateinit var mBinding: org.js.denisvieira.themoviedbapp.databinding.MovieDetailFragmentBinding
//
//    private val args: MovieDetailFragmentArgs by navArgs()
//
//    override fun onCreateView(
//            inflater: LayoutInflater, container: ViewGroup?,
//            savedInstanceState: Bundle?
//        ): View {
//            mBinding = DataBindingUtil.inflate(inflater, R.layout.movie_detail_activity, container, false)
//            mBinding.lifecycleOwner = this
//
//            setHasOptionsMenu(true)
//
//            return mBinding.root
//        }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        mDetailViewModel = ViewModelProviders.of(this)
//            .get(MovieDetailViewModel::class.java)
//
//        mDetailViewModel.loadMovieDetail(args.movieId)
//
//        startObservers()
//        setupToolbar()
//    }
//
//    private fun startObservers() {
//        startOnSuccessMainDataObserver()
//        startOnErrorMainDataObserver()
//    }
//
//    private fun setupToolbar() {
//        val toolbar = this.mBinding.movieDetailToolbar
//        val appCompatActivity = activity as AppCompatActivity?
//        val actionBar: AppCompatActivity? = appCompatActivity
//
//        actionBar?.setSupportActionBar(toolbar)
//        actionBar?.supportActionBar?.setDisplayShowTitleEnabled(true)
//        actionBar?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        actionBar?.title = getString(R.string.movie_detail_title)
//
//        toolbar.setNavigationOnClickListener {
//            findNavController().popBackStack()
//        }
//    }
//
//    private fun startOnSuccessMainDataObserver() {
//        mDetailViewModel.onSuccessMainDataObserver.observe(this, Observer {
//            mBinding.movie = mapMovieDetailDto(it!!)
//        })
//    }
//
//    private fun startOnErrorMainDataObserver() {
//        mDetailViewModel.onErrorMainDataObserver.observe(this, Observer {
//            if(it != null)
//                showAlertErrorByStatusCode(it,context!!)
//        })
//    }
//
//    private fun mapMovieDetailDto(movie: Movie): MovieDetailDto {
//        return MovieDetailDto(movie.id, movie.title, movie.posterPath, movie.backdropPath,
//                movie.genres,
//                movie.overview, movie.voteCount.toString(), movie.voteAverage.toString(),
//                movie.popularity.toString(), movie.runtime.toString(), formatReleaseDate(movie.releaseDate))
//    }
//
//    @SuppressLint("SimpleDateFormat")
//    fun formatReleaseDate(dateString: String?): String? {
//        val formatter = SimpleDateFormat("yyyy-MM-dd")
//
//        try {
//            val date : Date = formatter.parse(dateString) as Date
//            return date.formatToServerDateDefaults()
//        } catch (e : Exception) {
//
//        }
//
//        return getString(R.string.invalid_date)
//    }
//}
