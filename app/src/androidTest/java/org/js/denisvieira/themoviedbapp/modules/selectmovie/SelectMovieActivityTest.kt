package org.js.denisvieira.themoviedbapp.modules.selectmovie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import org.js.denisvieira.themoviedbapp.support.createPage
import org.js.denisvieira.themoviedbapp.support.fixtures.Seeds
import org.hamcrest.Matchers
import org.js.denisvieira.themoviedbapp.R
import org.js.denisvieira.themoviedbapp.modules.BaseFragmentTest
import org.junit.*
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

@RunWith(Enclosed::class)
@LargeTest
class SelectMovieActivityTest {

    abstract class DescribeLoginAction : BaseFragmentTest() {

        @Rule
        @JvmField
        var activityTestRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

        lateinit var loginPage: LoginPage

        @Before
        fun init() {
            loginPage = createPage<LoginPage>()
        }


    }

    class ContextWhenPassValidParams : DescribeLoginAction() {

        @Test
        fun it_should_call_select_construction_site_screen() {
            val selectConstructionSitePage = loginPage.doLogin(Seeds.UserLogin.complete)
            Assert.assertThat(selectConstructionSitePage.selectConstructionSiteToolbarTitle(),
                    Matchers.equalTo(getResourceString(R.string.select_construction_site_construction_site)))
        }

    }

    class ContextWhenPassEmptyParams : DescribeLoginAction() {

        @Before
        fun before() {
            loginPage.doLogin(Seeds.UserLogin.withoutData)
        }

        @Test
        fun it_should_show_empty_messages() {
            onView(withId(R.id.login_email_text_input_layout)).check(matches(hasTextInputLayoutErrorText(getResourceString(R.string
                    .login_errors_not_empty))))
            onView(withId(R.id.login_password_text_input_layout)).check(matches(hasTextInputLayoutErrorText(getResourceString(R.string
                    .login_errors_not_empty))))
        }
    }

    class ContextWhenPassInvalidEmail : DescribeLoginAction() {
        @Before
        fun before() {
            loginPage.doLogin(Seeds.UserLogin.wrongEmail)
        }

        @Test
        fun it_should_show_invalid_grant_message() {
            onView(withId(R.id.error_auth_text_view)).check(matches(withText(getResourceString(R.string
                    .login_errors_invalid_grant))))
        }
    }

    class ContextWhenPassInvalidPassword : DescribeLoginAction() {
        @Before
        fun before() {
            loginPage.doLogin(Seeds.UserLogin.wrongPassword)
        }

        @Test
        fun it_should_show_invalid_grant_message() {
            onView(withId(R.id.error_auth_text_view)).check(matches(withText(getResourceString(R.string
                    .login_errors_invalid_grant))))
        }
    }

}
