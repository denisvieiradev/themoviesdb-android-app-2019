package org.js.denisvieira.themoviedbapp.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.js.denisvieira.themoviedbapp.R

class MovieDetailPage : Page() {

    private val mOverviewText : Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_overview_text_view)
    private val mRuntimeText : Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_runtime_text_view)
    private val mReleaseDateText : Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_release_date_text_view)
    private val mPopularityText : Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_popularity_text_view)
    private val mVoteCountText: Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_vote_count_text_view)
    private val mVoteAverageText: Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_vote_average_text_view)
    private val mGenresText: Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_genres_text_view)
    private val mTitleText : Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_title_text_view)
    private val mPosterImage : Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_poster_image_view)
    private val mBackdropImage : Matcher<View>         = ViewMatchers.withId(R.id.movie_detail_backdrop_image_view)

    override fun trait(): Matcher<View> {
        return mTitleText
    }

}