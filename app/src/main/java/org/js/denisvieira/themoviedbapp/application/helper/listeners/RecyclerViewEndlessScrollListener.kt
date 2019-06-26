package org.js.denisvieira.themoviedbapp.application.helper.listeners

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewEndlessScrollListener(layoutManager: LinearLayoutManager) : EndlessScrollListener(layoutManager) {

    private val visibleThreshold = 3

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        if (isNotLoading(totalItemCount) && visibleThreshold + lastVisibleItemPosition > totalItemCount) {
            incrementCurrentPage()
            onLoadMore(currentPage, totalItemCount)
            setLoading(true)
        }
    }

}