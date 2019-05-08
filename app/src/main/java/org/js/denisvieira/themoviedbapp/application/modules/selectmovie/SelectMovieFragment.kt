//package org.js.denisvieira.themoviedbapp.application.modules.selectmovie
//
//import android.annotation.SuppressLint
//import android.app.SearchManager
//import android.content.Context
//import android.os.Bundle
//import android.view.*
//import android.view.inputmethod.InputMethodManager
//import androidx.appcompat.app.AppCompatActivity
//import androidx.appcompat.widget.SearchView
//import androidx.databinding.DataBindingUtil.inflate
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import androidx.navigation.fragment.findNavController
//import androidx.recyclerview.widget.LinearLayoutManager
//import kotlinx.android.synthetic.main.select_movie_activity.*
//import org.js.denisvieira.themoviedbapp.R
//import org.js.denisvieira.themoviedbapp.application.helper.RecyclerTouchListener
//import org.js.denisvieira.themoviedbapp.application.helper.listeners.RecyclerViewEndlessScrollListener
//import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.adapters.SelectMovieAdapter
//import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.dto.MovieDto
//import org.js.denisvieira.themoviedbapp.application.util.WrapContentLinearLayoutManager
//import org.js.denisvieira.themoviedbapp.application.util.extensions.formatToServerDateDefaults
//import org.js.denisvieira.themoviedbapp.application.util.extensions.observeEvent
//import org.js.denisvieira.themoviedbapp.application.util.showAlertErrorByStatusCode
//import org.js.denisvieira.themoviedbapp.domain.data.Cache
//import org.js.denisvieira.themoviedbapp.domain.model.genre.Genre
//import org.js.denisvieira.themoviedbapp.domain.model.movie.Movie
//import java.text.SimpleDateFormat
//import java.util.*
//
//
//class SelectMovieFragment : Fragment() {
//
//    private val firstPage = 1
//
//    private lateinit var mBinding: org.js.denisvieira.themoviedbapp.databinding.SelectMovieFragmentBinding
//    private lateinit var mSelectMovieViewModel: SelectMovieViewModel
//
//    private lateinit var mMoviesScrollListener: RecyclerViewEndlessScrollListener
//
//    private var mCurrentPage: Int                        = firstPage
//    private var mCurrentSearchText: String?              = null
//    private var mSelectMovieAdapter : SelectMovieAdapter = SelectMovieAdapter(arrayListOf())
//
//    companion object {
//        fun newInstance() = SelectMovieFragment()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        mBinding = inflate(inflater, R.layout.select_movie_activity, container, false)
//        mBinding.lifecycleOwner = this
//
//        setHasOptionsMenu(true)
//
//        return mBinding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        mSelectMovieViewModel = ViewModelProviders.of(this)
//            .get(SelectMovieViewModel::class.java)
//
//        mSelectMovieViewModel.loadMovieGenres()
//
//        startObservers()
//        setupComponents()
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        setupSearchViewComponent(menu)
//
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//
//    private fun setupComponents() {
//        setupToolbar()
//        setupMainRecyclerView()
//        setupOnItemTouchListenerToRecyclerView()
//    }
//
//
//    private fun setupOnItemTouchListenerToRecyclerView() {
//        selectMovieMainRecyclerView.addOnItemTouchListener(RecyclerTouchListener(context!!,
//            selectMovieMainRecyclerView, object : RecyclerTouchListener.SimpleOnClickListener {
//                override fun onClick(view: View, position: Int) {
//                    val itemId = mSelectMovieAdapter.movies[position].id
//                    val actionToMovieDetail = SelectMovieFragmentDirections.
//                        actionSelectMovieFragmentToMovieDetailFragment().setMovieId(itemId!!)
//
//                    findNavController().navigate(actionToMovieDetail)
//                }
//
//            }))
//    }
//
//    private fun setupMainRecyclerView() {
//        val linearLayoutManager = getDefaultLinearLayoutManager(context)
//        mMoviesScrollListener   = getDefaultScrollListenerToMainRecyclerView(linearLayoutManager)
//
//        selectMovieMainRecyclerView.adapter                  = mSelectMovieAdapter
//        selectMovieMainRecyclerView.isNestedScrollingEnabled = false
//        selectMovieMainRecyclerView.layoutManager            = linearLayoutManager
//
//        selectMovieMainRecyclerView.setHasFixedSize(true)
//        selectMovieMainRecyclerView.addOnScrollListener(mMoviesScrollListener)
//    }
//
//    private fun getDefaultScrollListenerToMainRecyclerView(linearLayoutManager: LinearLayoutManager): RecyclerViewEndlessScrollListener {
//        return object: RecyclerViewEndlessScrollListener(linearLayoutManager) {
//            override fun onLoadMore(page: Int, totalItemsCount: Int) {
//                if (mCurrentPage == page) {
//                    mCurrentPage++
//
//                    loadMovies(mCurrentSearchText ?: "", mCurrentPage)
//                }
//            }
//        }
//    }
//
//    private fun loadMovies(searchText: String, page: Int) {
//        if (searchText.isBlank()) {
//            mSelectMovieViewModel.loadPopularMovies(page)
//        } else {
//            mSelectMovieViewModel.searchMovies(searchText, page)
//        }
//    }
//
//    private fun getDefaultLinearLayoutManager(context: Context?): LinearLayoutManager {
//        return WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//    }
//
//    private fun setupToolbar() {
//        val toolbar = this.mBinding.selectMovieToolbar
//        val appCompatActivity = activity as AppCompatActivity?
//        val actionBar: AppCompatActivity? = appCompatActivity
//
//        actionBar?.setSupportActionBar(toolbar)
//        actionBar?.supportActionBar?.setDisplayShowTitleEnabled(true)
//        actionBar?.title = getString(R.string.app_name)
//    }
//
//
//    private fun setupSearchViewComponent(menu: Menu) {
//        activity!!.menuInflater.inflate(R.menu.options_menu, menu)
//
//        val searchItem = menu.findItem(R.id.action_search)
//        val searchManager = context!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val searchView: SearchView? = getSearchView(searchItem)
//
//        searchView?.setOnQueryTextListener(getOnSearchTextListener())
//        searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
//    }
//
//    private fun getSearchView(menuItem : MenuItem): SearchView? {
//        return menuItem.actionView as SearchView?
//    }
//
//    private fun getOnSearchTextListener(): SearchView.OnQueryTextListener {
//        return object: SearchView.OnQueryTextListener {
//            override fun onQueryTextChange(queryText: String): Boolean {
//                if(isBlankAndSearchNotFilledInitially(queryText, mCurrentSearchText)){
//                    searchMoviesAfterSearch()
//                }
//
//                mCurrentSearchText = queryText
//                return true
//            }
//
//            override fun onQueryTextSubmit(queryText: String): Boolean {
//                searchMoviesAfterSearch(queryText)
//                return true
//            }
//
//        }
//    }
//
//    private fun isBlankAndSearchNotFilledInitially(queryText: String, currentSearchText: String?): Boolean {
//        return queryText.isBlank() && currentSearchText != null
//    }
//
//    private fun searchMoviesAfterSearch(queryText: String = "") {
//        resetStateOnSubmitTextOnSearch()
//
//        if(queryText.isBlank()){
//            mSelectMovieViewModel.loadPopularMovies(mCurrentPage)
//        } else {
//            mSelectMovieAdapter.resetStatus()
//            mSelectMovieViewModel.searchMovies(queryText, mCurrentPage)
//        }
//    }
//
//    private fun resetStateOnSubmitTextOnSearch() {
//        mCurrentPage       = firstPage
//        mCurrentSearchText = ""
//
//        mSelectMovieAdapter.resetStatus()
//        mMoviesScrollListener.resetState()
//    }
//
//
//    private fun startObservers() {
//        startLoadGenresSuccessObserver()
//        startOnSuccessMainDataObserver()
//        startOnErrorMainDataObserver()
//        startIsLoadingMainDataObserver()
//    }
//
//    private fun startOnSuccessMainDataObserver() {
//        mSelectMovieViewModel.mSelectMovieObservers.onSuccessMainDataObserver.observeEvent(viewLifecycleOwner){ response ->
//            if(isNotNullOrEmpty(response)){
//                val newMovies = mapMovieDtos(response)
//
//                mSelectMovieAdapter.addAllMovies(newMovies,
//                    getNotifyItemInsertedWithSuccessAtPositionCallback() )
//
//                mSelectMovieAdapter.notifyDataSetChanged()
//
//                mBinding.progressBar.visibility = View.GONE
//            }
//        }
//    }
//
//    private fun startOnErrorMainDataObserver() {
//        mSelectMovieViewModel.mSelectMovieObservers.onErrorMainDataObserver.observe(viewLifecycleOwner, Observer {
//            if(it != null)
//                showAlertErrorByStatusCode(it,context!!)
//        })
//    }
//
//    private fun mapMovieDtos(response: List<Movie>) : List<MovieDto>{
//        return response.map {
//                movie -> mapMovieDto(movie)}
//    }
//
//    private fun mapMovieDto(movie: Movie): MovieDto {
//        return MovieDto(movie.id, movie.title, movie.posterPath, movie.genres, formatReleaseDate(movie.releaseDate))
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
//
//
//    private fun isNotNullOrEmpty(response: List<Movie>?): Boolean {
//        return !response.isNullOrEmpty()
//    }
//
//    private fun startLoadGenresSuccessObserver() {
//        mSelectMovieViewModel.loadGenresSuccessObserver.observe(viewLifecycleOwner, Observer {
//            setGenresInCache(it)
//            mSelectMovieViewModel.loadPopularMovies(firstPage)
//        })
//    }
//
//    private fun setGenresInCache(genres :List<Genre>?) {
//        if (genres != null)
//            Cache.cacheGenres(genres)
//    }
//
//    private fun startIsLoadingMainDataObserver() {
//        mSelectMovieViewModel.mSelectMovieObservers.isLoadingMainDataObserver.observe(this, Observer {
//            if(it == true) showLoadingRecyclerViewFooter() else hideLoadingRecyclerViewFooter()
//        })
//    }
//
//
//    private fun showLoadingRecyclerViewFooter() {
//        mSelectMovieAdapter.showLoadingFooter(getNotifyItemInsertedWithSuccessAtPositionCallback())
//    }
//
//    private fun hideLoadingRecyclerViewFooter() {
//        mSelectMovieAdapter.hideLoadingFooter(object: SelectMovieAdapter.MoviesAdapterItemCallback {
//            override fun successAtPositionCallback(position: Int) {
//                mBinding.selectMovieMainRecyclerView.post {
//                    mSelectMovieAdapter.notifyItemRemoved(position)
//                }
//            }
//        })
//    }
//
//    private fun getNotifyItemInsertedWithSuccessAtPositionCallback(): SelectMovieAdapter.MoviesAdapterItemCallback {
//        return object : SelectMovieAdapter.MoviesAdapterItemCallback {
//            override fun successAtPositionCallback(position: Int) {
//                mBinding.selectMovieMainRecyclerView.post {
//                    mSelectMovieAdapter.notifyItemInserted(position)
//                }
//            }
//        }
//    }
//
//    private fun hideKeyboard() {
//        val inputManager: InputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputManager.hideSoftInputFromWindow(activity!!.currentFocus.windowToken, InputMethodManager.SHOW_FORCED)
//    }
//
//
//}