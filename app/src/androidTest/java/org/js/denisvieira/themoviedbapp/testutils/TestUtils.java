package org.js.denisvieira.themoviedbapp.testutils;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static java.lang.Thread.sleep;

public class TestUtils {

    public static void waitEspresso(long milles) {
        try {
            sleep(milles);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void checkIfIdIsDisplayed(int id){
        onView(withId(id)).check(matches(isDisplayed()));
    }

    public static void checkIfIdIsNotVisible(int id){
        onView(withId(id)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    public static void findAndClick(int id){
        onView(withId(id)).perform(click());
    }

    public static String getResourceString(int id) {
        Context targetContext = InstrumentationRegistry.getTargetContext();
        return targetContext.getResources().getString(id);
    }

    public static Matcher<View> hasTextInputLayoutErrorText(final String expectedErrorText) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextInputLayout)) {
                    return false;
                }

                CharSequence error = ((TextInputLayout) view).getError();

                if (error == null) {
                    return false;
                }

                String hint = error.toString();

                return expectedErrorText.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}