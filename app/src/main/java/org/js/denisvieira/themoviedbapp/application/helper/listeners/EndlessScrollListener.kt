package org.js.denisvieira.themoviedbapp.application.helper.listeners

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class EndlessScrollListener(private val mLayoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var previousTotalItemCount = 0
    private var loading                = true
    private val startingPageIndex      = 0
    private val pageSize               = 20
    var currentPage                    = 0

    val lastVisibleItemPosition: Int
        get() = mLayoutManager.findLastVisibleItemPosition()

    val totalItemCount: Int
        get() = mLayoutManager.itemCount

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {

    }

    protected fun isNotLoading(totalItemCount: Int): Boolean {
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount

            if (totalItemCount == 0) {
                this.loading = true
            }
        }

        if (loading && (totalItemCount - pageSize) == previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        return !loading
    }

    protected fun setLoading(loading: Boolean) {
        this.loading = loading
    }

    fun resetState() {
        this.previousTotalItemCount = 0
        this.currentPage = this.startingPageIndex
        this.loading = true
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int)

    fun incrementCurrentPage() {
        currentPage++
    }

}