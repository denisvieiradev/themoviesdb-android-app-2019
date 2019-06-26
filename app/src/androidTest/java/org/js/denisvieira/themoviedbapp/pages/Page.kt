package org.js.denisvieira.themoviedbapp.pages

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import org.js.denisvieira.themoviedbapp.support.customs.ViewWith
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

abstract class Page {

    abstract fun trait(): Matcher<View>

    fun checkTrait(): Boolean {
        var result: Boolean = true
        try {
            onView(trait()).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } catch (e: NoMatchingViewException) {
            result = false
        } catch (e: AssertionFailedError) {
            result = false
        }

        return result
    }

    fun findView(vararg matcher: Matcher<View>): ViewInteraction {

        return onView(allOf<View>(*matcher))
    }

    fun viewIsVisible(vararg matcher: Matcher<View>): ViewInteraction {
        return onView(allOf<View>(*matcher)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    fun waitAndDo(matcher: Matcher<View>, vararg actions: ViewAction) {
        findView(matcher = *arrayOf(matcher)).perform(*actions)
    }

    fun waitAndType(on: Matcher<View>, text: String) {
        waitAndDo(on, ViewActions.replaceText(text), ViewActions.closeSoftKeyboard())
    }

    fun waitAndTypeAndPressImeActionButton(on: Matcher<View>, text: String) {
        waitAndDo(on, ViewActions.replaceText(text), ViewActions.pressImeActionButton())
    }

    fun click(on: Matcher<View>) {
        waitAndDo(on, ViewActions.click())
    }

    fun message(input: ViewWith): String {

        findView(input)
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val textView = input.view as TextView
        return textView.text.toString()
    }

    fun hint(input: ViewWith): String {

        findView(input)
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val textView = input.view as TextView
        return textView.hint.toString()
    }


}
