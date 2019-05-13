package org.js.denisvieira.themoviedbapp.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher
import org.js.denisvieira.themoviedbapp.R

class MovieDetailPage : Page() {

    val mOverviewText : Matcher<View>    = ViewMatchers.withId(R.id.movie_detail_overview_text_view)
    val mRuntimeText : Matcher<View>     = ViewMatchers.withId(R.id.movie_detail_runtime_text_view)
    val mReleaseDateText : Matcher<View> = ViewMatchers.withId(R.id.movie_detail_release_date_text_view)
    val mPopularityText : Matcher<View>  = ViewMatchers.withId(R.id.movie_detail_popularity_text_view)
    val mVoteCountText: Matcher<View>    = ViewMatchers.withId(R.id.movie_detail_vote_count_text_view)
    val mVoteAverageText: Matcher<View>  = ViewMatchers.withId(R.id.movie_detail_vote_average_text_view)
    val mGenresText: Matcher<View>       = ViewMatchers.withId(R.id.movie_detail_genres_text_view)
    val mTitleText : Matcher<View>       = ViewMatchers.withId(R.id.movie_detail_title_text_view)
    val mPosterImage : Matcher<View>     = ViewMatchers.withId(R.id.movie_detail_poster_image_view)
    val mBackdropImage : Matcher<View>   = ViewMatchers.withId(R.id.movie_detail_backdrop_image_view)

    override fun trait(): Matcher<View> {
        return mTitleText
    }

}