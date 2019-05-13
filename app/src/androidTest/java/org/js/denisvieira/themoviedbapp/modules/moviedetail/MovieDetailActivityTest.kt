package org.js.denisvieira.themoviedbapp.modules.moviedetail

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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

        private val movieTitleText = "Teste"
        private val movieMountedGenresText = "Drama, Action, Terror"
        private val movieVoteCountText = "10"
        private val movieRuntimeText = "90 minutes"
        private val movieVoteAverageText = "9.0"
        private val movieOverviewText = "Lorem Ipsum"

        @Test
        fun it_should_show_correct_movie_title() {
            onView(mMovieDetailPage.mTitleText).check(
                matches(withText(movieTitleText)))
        }

        @Test
        fun it_should_show_genres_text_with_all_genres_correctly() {
            onView(mMovieDetailPage.mGenresText).check(
                matches(withText(movieMountedGenresText)))
        }

        @Test
        fun it_should_show_vote_average_with_correct_data() {
            onView(mMovieDetailPage.mVoteAverageText).check(
                matches(withText(movieVoteAverageText)))
        }

        @Test
        fun it_should_show_movie_runtime_with_correct_text() {
            onView(mMovieDetailPage.mRuntimeText).check(
                matches(withText(movieRuntimeText)))
        }

        @Test
        fun it_should_show_vote_count_with_correct_text() {
            onView(mMovieDetailPage.mVoteCountText).check(
                matches(withText(movieVoteCountText)))
        }

        @Test
        fun it_should_show_overview_text_with_correct_text() {
            onView(mMovieDetailPage.mOverviewText).check(
                matches(withText(movieOverviewText)))
        }

    }


}
