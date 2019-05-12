package org.js.denisvieira.themoviedbapp.modules.moviedetail

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.application.modules.moviedetail.MovieDetailActivity
import org.js.denisvieira.themoviedbapp.application.modules.selectmovie.SelectMovieActivity
import org.js.denisvieira.themoviedbapp.domain.constants.AppKeys
import org.js.denisvieira.themoviedbapp.modules.BaseFragmentTest
import org.js.denisvieira.themoviedbapp.pages.MovieDetailPage
import org.js.denisvieira.themoviedbapp.support.createPage
import org.js.denisvieira.themoviedbapp.testutils.IntentFactory
import org.js.denisvieira.themoviedbapp.testutils.RecyclerViewMatcher.withRecyclerView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

/**
 * Ui tests for the implementation of [MovieDetailActivity]
 */

@RunWith(Enclosed::class)
@LargeTest
class MovieDetailActivityTest {

    abstract class DescribeMovieDetailActivity : BaseFragmentTest() {

        @Rule
        @JvmField
        var mActivityTestRule: ActivityTestRule<MovieDetailActivity> =
            ActivityTestRule(MovieDetailActivity::class.java, true, false)

        lateinit var mMovieDetailPage: MovieDetailPage

        @Before
        fun init() {
            val intent = IntentFactory.createIntentWithBundle(MovieDetailActivity::class.java, generateBundle())
            mActivityTestRule.launchActivity(intent)

            mMovieDetailPage = createPage()
        }

        private fun generateBundle(): Bundle {
            val bundle = Bundle()
            bundle.putInt(AppKeys.KEY_MOVIE_ID, 1)
            return bundle
        }


    }

    class ContextWhenLoadMovieDetailData : DescribeMovieDetailActivity() {

        @Test
        fun it_should_show_first_movie_item_of_the_list_correctly() {

        }

    }


}
