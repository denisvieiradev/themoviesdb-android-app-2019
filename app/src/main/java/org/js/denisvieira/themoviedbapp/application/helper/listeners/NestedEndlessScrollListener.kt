package org.js.denisvieira.themoviedbapp.application.helper.listeners

import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager

abstract class NestedEndlessScrollListener(layoutManager: LinearLayoutManager) : EndlessScrollListener(layoutManager), NestedScrollView.OnScrollChangeListener {

    override fun onScrollChange(view: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
        if (view.getChildAt(view.childCount - 1) != null) {
            if (scrollY >= view.getChildAt(view.childCount - 1).measuredHeight - view.measuredHeight - 200 && scrollY > oldScrollY) {

                if (isNotLoading(totalItemCount)) {
                    incrementCurrentPage()
                    onLoadMore(currentPage, totalItemCount)
                    setLoading(true)
                }
            }
        }
    }

}