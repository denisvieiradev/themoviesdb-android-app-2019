package org.js.denisvieira.themoviedbapp.modules.selectmovie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.SelectMovieActivity
import org.js.denisvieira.themoviedbapp.modules.BaseFragmentTest
import org.js.denisvieira.themoviedbapp.modules.moviedetail.MovieDetailActivityTest
import org.js.denisvieira.themoviedbapp.pages.SelectMoviePage
import org.js.denisvieira.themoviedbapp.support.createPage
import org.js.denisvieira.themoviedbapp.testutils.RecyclerViewMatcher.withRecyclerView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * Ui tests for the implementation of [SelectMovieActivity]
 */

@RunWith(Enclosed::class)
@LargeTest
class SelectMovieActivityTest {

    abstract class DescribeSelectMovieActivity : BaseFragmentTest() {

        @Rule
        @JvmField
        var activityTestRule: ActivityTestRule<SelectMovieActivity> = ActivityTestRule(SelectMovieActivity::class.java)

        lateinit var mSelectMoviePage: SelectMoviePage

        @Before
        fun init() {
            mSelectMoviePage = createPage()
        }

    }

    class ContextWhenLoadMovieList : DescribeSelectMovieActivity() {

        @Test
        fun it_should_show_first_movie_item_of_the_list_correctly() {
            onView(withRecyclerView(R.id.select_movie_main_recycler_view)
                .atPositionOnView(0, R.id.movie_item_title_text_view))
                .check(matches(isDisplayed()))
        }

    }


    abstract class ContextWhenSearchMovieWithFoundItems : DescribeSelectMovieActivity() {

        private val searchText = "Movie Title 2"

        @Before
        fun clickOnSearchIconAndType() {
            mSelectMoviePage.doSearch(searchText)
        }

        @Test
        fun itShouldShowFirstElementWithSearchTextTitle() {
            onView(withRecyclerView(R.id.select_movie_main_recycler_view)
                .atPositionOnView(0, R.id.movie_item_title_text_view))
                .check(matches(withText(searchText)))
        }

    }

    class ContextWhenSearchMovieWithNotFoundItems : DescribeSelectMovieActivity() {

        private val searchText = "Item X"

        @Before
        fun clickOnSearchIconAndType() {
            mSelectMoviePage.doSearch(searchText)
        }

        @Test
        fun itShouldShowNoElementFoundTextView() {
            mSelectMoviePage.isShowEmptyListTextView()
        }

    }
}
