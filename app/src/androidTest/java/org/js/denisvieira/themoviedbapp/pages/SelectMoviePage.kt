package org.js.denisvieira.themoviedbapp.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.support.fixtures.Seeds.DEFAULT_SEARCH

class SelectMoviePage : Page() {

    private val mMainToolbar : Matcher<View>         = ViewMatchers.withId(R.id.select_movie_main_toolbar)

    override fun trait(): Matcher<View> {
        return mMainToolbar
    }

    private fun fillSearch(searchText: String) {
        waitAndType(on = mMainToolbar, text = searchText)
    }

    fun doSearch(){
        fillSearch(DEFAULT_SEARCH)
        click(on = mMainToolbar)
    }


}