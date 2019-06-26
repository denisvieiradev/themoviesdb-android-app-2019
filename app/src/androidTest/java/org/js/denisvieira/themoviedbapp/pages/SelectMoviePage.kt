package org.js.denisvieira.themoviedbapp.pages

import android.view.View
import android.widget.EditText
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import org.hamcrest.Matcher
import org.js.denisvieira.themoviedbapp.R


class SelectMoviePage : Page() {

    private val mMainToolbar : Matcher<View>         = ViewMatchers.withId(R.id.select_movie_main_toolbar)
    private val mSearchIcon  : Matcher<View>         = ViewMatchers.withId(R.id.action_search)
    private val mEmptyListTextView : Matcher<View>   = ViewMatchers.withId(R.id.select_movie_empty_list_text_view)

    override fun trait(): Matcher<View> {
        return mMainToolbar
    }

    fun doSearch(searchText: String) {
        click(mSearchIcon)
        waitAndTypeAndPressImeActionButton(on = isAssignableFrom(EditText::class.java), text = searchText)
    }

    fun isShowEmptyListTextView(): ViewInteraction {
        return viewIsVisible(mEmptyListTextView)
    }

}