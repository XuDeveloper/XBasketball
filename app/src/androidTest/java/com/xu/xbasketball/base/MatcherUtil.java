package com.xu.xbasketball.base;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by xu on 2019/4/10.
 */
public class MatcherUtil {

    public static Matcher<View> withCollapsingToolbarLayoutText(Matcher<String> stringMatcher) {
        return new BoundedMatcher<View, CollapsingToolbarLayout>(CollapsingToolbarLayout.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with CollapsingToolbarLayout title: ");
                stringMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(CollapsingToolbarLayout collapsingToolbarLayout) {
                return stringMatcher.matches(collapsingToolbarLayout.getTitle());
            }
        };
    }

    public static Matcher<View> withToolbarText(Matcher<String> stringMatcher) {
        return new BoundedMatcher<View, Toolbar>(Toolbar.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with Toolbar title: ");
                stringMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(Toolbar toolbar) {
                return stringMatcher.matches(toolbar.getTitle());
            }
        };
    }

}
