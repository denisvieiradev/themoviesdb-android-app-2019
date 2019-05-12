package org.js.denisvieira.themoviedbapp.application.modules.selectmovie

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.application.helper.RecyclerTouchListener
import org.js.denisvieira.themoviedbapp.application.helper.listeners.RecyclerViewEndlessScrollListener
import org.js.denisvieira.themoviedbapp.application.modules.moviedetail.MovieDetailActivity
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.adapters.SelectMovieAdapter
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.dto.MovieItemDtoConverter
import org.js.denisvieira.themoviedbapp.application.util.WrapContentLinearLayoutManager
import org.js.denisvieira.themoviedbapp.application.util.showAlertErrorByStatusCode
import org.js.denisvieira.themoviedbapp.databinding.SelectMovieActivityBinding
import org.js.denisvieira.themoviedbapp.domain.constants.AppKeys.KEY_MOVIE_ID
import org.js.denisvieira.themoviedbapp.domain.data.Cache
import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie

class SelectMovieActivity : AppCompatActivity() {

    private val firstPage = 1

    private lateinit var mSelectMovieViewModel: SelectMovieViewModel
    private lateinit var mBinding: SelectMovieActivityBinding
    private lateinit var mMoviesScrollListener: RecyclerViewEndlessScrollListener

    private var mCurrentPage: Int = firstPage
    private var mCurrentSearchText: String? = null
    private var mSelectMovieAdapter: SelectMovieAdapter = SelectMovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.select_movie_activity)
        mSelectMovieViewModel = ViewModelProviders.of(this).get(SelectMovieViewModel::class.java)
        mBinding.viewModel = mSelectMovieViewModel
        mBinding.lifecycleOwner = this

        mSelectMovieViewModel.loadMovieGenres()

        startObservers()
        setupComponents()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        setupSearchViewComponent(menu)

        return super.onCreateOptionsMenu(menu)
    }

    private fun startObservers() {
        startLoadGenresSuccessObserver()
        startOnSuccessMainDataObserver()
        startOnErrorMainDataObserver()
        startIsLoadingMainDataObserver()
    }

    private fun setupComponents() {
        setupToolbar()
        setupMainRecyclerView()
        configureOnItemTouchListenerOnMainRecyclerView()
        setupSwipeRefreshLayout()
    }

    private fun setupSwipeRefreshLayout() {
        mBinding.selectMovieMainSwipeRefreshLayout.setOnRefreshListener {
            searchMoviesAfterSearch(mCurrentSearchText ?: "")
        }
    }

    private fun setupToolbar() {
        val toolbar = this.mBinding.selectMovieMainToolbar
        val appCompatActivity = this as AppCompatActivity?
        val actionBar: AppCompatActivity? = appCompatActivity

        actionBar?.setSupportActionBar(toolbar)
        actionBar?.supportActionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.title = getString(R.string.app_name)
    }

    private fun setupSearchViewComponent(menu: Menu) {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem.actionView as SearchView?

        searchView?.setOnQueryTextListener(getOnSearchTextListener())
        searchView?.setSearchableInfo(searchManager.getSearchableInfo(componentName))
    }

    private fun getOnSearchTextListener(): SearchView.OnQueryTextListener {
        return object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(queryText: String): Boolean {
                if (isBlankAndSearchNotFilledInitially(queryText, mCurrentSearchText)) {
                    searchMoviesAfterSearch()
                }

                mCurrentSearchText = queryText

                return true
            }

            override fun onQueryTextSubmit(queryText: String): Boolean {
                searchMoviesAfterSearch(queryText)
                return true
            }
        }
    }

    private fun isBlankAndSearchNotFilledInitially(queryText: String, currentSearchText: String?): Boolean {
        return queryText.isBlank() && currentSearchText != null
    }

    private fun searchMoviesAfterSearch(queryText: String = "") {
        resetStateOnSubmitTextOnSearch()

        if (queryText.isBlank()) {
            mSelectMovieViewModel.loadPopularMovies(mCurrentPage)
        } else {
            mSelectMovieViewModel.searchMovies(queryText, mCurrentPage)
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus.windowToken, InputMethodManager.SHOW_FORCED)
    }

    private fun setupMainRecyclerView() {
        val linearLayoutManager = getDefaultLinearLayoutManager(this)
        mMoviesScrollListener = getDefaultScrollListenerToMainRecyclerView(linearLayoutManager)

        mBinding.selectMovieMainRecyclerView.adapter = mSelectMovieAdapter
        mBinding.selectMovieMainRecyclerView.isNestedScrollingEnabled = false
        mBinding.selectMovieMainRecyclerView.layoutManager = linearLayoutManager

        mBinding.selectMovieMainRecyclerView.setHasFixedSize(true)
        mBinding.selectMovieMainRecyclerView.addOnScrollListener(mMoviesScrollListener)
    }

    private fun getDefaultScrollListenerToMainRecyclerView(linearLayoutManager: LinearLayoutManager): RecyclerViewEndlessScrollListener {
        return object : RecyclerViewEndlessScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                if (mCurrentPage == page) {
                    mCurrentPage++

                    loadMoreMoviesOnScroll(mCurrentSearchText ?: "", mCurrentPage)
                }
            }
        }
    }

    private fun loadMoreMoviesOnScroll(searchText: String, page: Int) {
        mBinding.selectMovieEmptyListTextView.visibility = GONE

        if (searchText.isBlank()) {
            mSelectMovieViewModel.loadPopularMovies(page)
        } else {
            mSelectMovieViewModel.searchMovies(searchText, page)
        }
    }

    private fun configureOnItemTouchListenerOnMainRecyclerView() {
        mBinding.selectMovieMainRecyclerView.addOnItemTouchListener(
            RecyclerTouchListener(this,
                mBinding.selectMovieMainRecyclerView, object : RecyclerTouchListener.SimpleOnClickListener {
                    override fun onClick(view: View, position: Int) {
                        val itemId = mSelectMovieAdapter.movieItems[position].id

                        itemId?.let { goToMovieDetailScreen(it) }
                    }

                })
        )
    }

    private fun goToMovieDetailScreen(itemId: Int) {
        val intent = Intent(applicationContext, MovieDetailActivity::class.java)
        val bundle = Bundle()

        bundle.putInt(KEY_MOVIE_ID, itemId!!)
        intent.putExtras(bundle)

        startActivity(intent)
    }

    private fun getDefaultLinearLayoutManager(context: Context?): LinearLayoutManager {
        return WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun startOnErrorMainDataObserver() {
        mSelectMovieViewModel.onErrorMainDataObserver.observe(this, Observer {
            if (it != null)
                showAlertErrorByStatusCode(it, this)
        })
    }

    private fun resetStateOnSubmitTextOnSearch() {
        mCurrentPage = firstPage

        mBinding.selectMovieEmptyListTextView.visibility = GONE

        mSelectMovieAdapter.resetStatus()
        mMoviesScrollListener.resetState()
    }

    private fun startOnSuccessMainDataObserver() {
        mSelectMovieViewModel.onSuccessMainDataObserver.observe(this, Observer { response ->
            if (isNotNullOrEmptyOnMoviesResponse(response)) {
                val newMovies = MovieItemDtoConverter.convertEntitiesToDtos(response ?: arrayListOf(), this)

                mSelectMovieAdapter.addAllMovies(
                    newMovies,
                    getNotifyItemInsertedWithSuccessAtPositionCallback()
                )

                mSelectMovieAdapter.notifyDataSetChanged()
                mBinding.selectMovieMainSwipeRefreshLayout.isRefreshing = false

            } else if (isNullOrEmptyAndIsFirstPageOnMoviesResponse(response)) {
                mBinding.selectMovieEmptyListTextView.visibility = VISIBLE
            }

            mBinding.selectMovieMainContentLoadingProgressBar.visibility = GONE
        })
    }

    private fun isNullOrEmptyAndIsFirstPageOnMoviesResponse(response: List<Movie>?): Boolean {
        return response.isNullOrEmpty() && mCurrentPage == 1
    }

    private fun isNotNullOrEmptyOnMoviesResponse(response: List<Movie>?): Boolean {
        return !response.isNullOrEmpty()
    }

    private fun startLoadGenresSuccessObserver() {
        mSelectMovieViewModel.loadGenresSuccessObserver.observe(this, Observer {
            setGenresInCache(it)
            mSelectMovieViewModel.loadPopularMovies(firstPage)
        })
    }

    private fun setGenresInCache(genres: List<Genre>?) {
        if (genres != null)
            Cache.cacheGenres(genres)
    }

    private fun startIsLoadingMainDataObserver() {
        mSelectMovieViewModel.isLoadingMainDataObserver.observe(this, Observer {
            if (it == true) showLoadingIconOnRecyclerViewFooter() else hideLoadingIconOnRecyclerViewFooter()
        })
    }

    private fun showLoadingIconOnRecyclerViewFooter() {
        mSelectMovieAdapter.showLoadingFooter(getNotifyItemInsertedWithSuccessAtPositionCallback())
    }

    private fun hideLoadingIconOnRecyclerViewFooter() {
        mSelectMovieAdapter.hideLoadingFooter(object : SelectMovieAdapter.MoviesAdapterItemCallback {
            override fun successAtPositionCallback(position: Int) {
                mBinding.selectMovieMainRecyclerView.post {
                    mSelectMovieAdapter.notifyItemRemoved(position)
                }
            }
        })
    }

    private fun getNotifyItemInsertedWithSuccessAtPositionCallback(): SelectMovieAdapter.MoviesAdapterItemCallback {
        return object : SelectMovieAdapter.MoviesAdapterItemCallback {
            override fun successAtPositionCallback(position: Int) {
                mBinding.selectMovieMainRecyclerView.post {
                    mSelectMovieAdapter.notifyItemInserted(position)
                }
            }
        }
    }

}

