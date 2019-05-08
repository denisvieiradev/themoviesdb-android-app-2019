package org.js.denisvieira.themoviedbapp.testutils;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.util.HumanReadables;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.annimon.stream.Optional;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static android.support.test.espresso.matcher.ViewMatchers.*;

public class CustomScrollActions {

    public static ViewAction nestedScrollTo() {

        final String NESTED_SCROLL_VIEW_DESCRIPTION = "Find parent with type " + NestedScrollView.class +
                " of matched view and programmatically scroll to it.";

        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return Matchers.allOf(
                        isDescendantOfA(isAssignableFrom(NestedScrollView.class)),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE));
            }

            @Override
            public String getDescription() {
                return NESTED_SCROLL_VIEW_DESCRIPTION;
            }

            @Override
            public void perform(UiController uiController, View view) {
                try {
                    NestedScrollView nestedScrollView = (NestedScrollView)
                            findFirstParentLayoutOfClass(view, NestedScrollView.class);

                    Optional.ofNullable(nestedScrollView).ifPresentOrElse(
                            nested ->
                                setCoordinatorLayout(nestedScrollView, view),
                            () -> {
                                try {
                                    throw new Exception("Unable to find NestedScrollView parent.");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                } catch (Exception e) {
                    throw new PerformException.Builder()
                            .withActionDescription(this.getDescription())
                            .withViewDescription(HumanReadables.describe(view))
                            .withCause(e)
                            .build();
                }
                uiController.loopMainThreadUntilIdle();
            }
        };
    }

    private static void setCoordinatorLayout(NestedScrollView nestedScrollView, View view) {
        CoordinatorLayout coordinatorLayout =
                (CoordinatorLayout) findFirstParentLayoutOfClass(view, CoordinatorLayout.class);

        Optional.ofNullable(coordinatorLayout).executeIfPresent(
                layout ->
                    setCollapsingToolbar(nestedScrollView, coordinatorLayout)
        );

        nestedScrollView.scrollTo(0, view.getTop());
    }

    private static void setCollapsingToolbar(NestedScrollView nestedScrollView, CoordinatorLayout coordinatorLayout) {
        CollapsingToolbarLayout collapsingToolbarLayout =
                findCollapsingToolbarLayoutChildIn(coordinatorLayout);

        Optional.ofNullable(collapsingToolbarLayout).executeIfPresent(
          toolbar -> {
              int toolbarHeight = collapsingToolbarLayout.getHeight();
              nestedScrollView.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL);
              nestedScrollView.dispatchNestedPreScroll(0, toolbarHeight, null, null);
          }
        );
    }

    private static CollapsingToolbarLayout findCollapsingToolbarLayoutChildIn(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof CollapsingToolbarLayout) {
                return (CollapsingToolbarLayout) child;
            } else if (child instanceof ViewGroup) {
                return findCollapsingToolbarLayoutChildIn((ViewGroup) child);
            }
        }
        return null;
    }

    private static View findFirstParentLayoutOfClass(View view, Class<? extends View> parentClass) {
        ViewParent parent        = new FrameLayout(view.getContext());
        ViewParent incrementView = null;
        int i = 0;
        while (parent != null && !(parent.getClass() == parentClass)) {
            if (i == 0) {
                parent = findParent(view);
            } else {
                parent = findParent(incrementView);
            }
            incrementView = parent;
            i++;
        }
        return (View) parent;
    }

    private static ViewParent findParent(View view) {
        return view.getParent();
    }

    private static ViewParent findParent(ViewParent view) {
        return view.getParent();
    }
}