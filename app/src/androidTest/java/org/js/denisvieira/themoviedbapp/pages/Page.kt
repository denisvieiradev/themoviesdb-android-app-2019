package br.com.stant.stant_android_occuPagerrences.new.pages

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.ViewAction
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import android.widget.TextView
import br.com.stant.stant_android_occurrences.support.customs.ViewWith
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

abstract class Page {

    abstract fun trait(): org.hamcrest.Matcher<View>

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

    @JvmOverloads
    fun findView(vararg matcher: Matcher<View>): ViewInteraction {

        return onView(allOf<View>(*matcher))
    }

    fun waitAndDo(matcher: Matcher<View>, vararg actions: ViewAction) {
        findView(matcher = *arrayOf(matcher)).perform(*actions)
    }

    @JvmOverloads
    fun waitAndType(on: Matcher<View>, text: String) {
        waitAndDo(on, ViewActions.replaceText(text), ViewActions.closeSoftKeyboard())
    }

    @JvmOverloads
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
