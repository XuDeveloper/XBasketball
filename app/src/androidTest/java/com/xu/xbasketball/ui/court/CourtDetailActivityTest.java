package com.xu.xbasketball.ui.court;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.app.TestApp;
import com.xu.xbasketball.base.BaseUITest;
import com.xu.xbasketball.base.TestConstants;
import com.xu.xbasketball.di.DaggerTestActivityComponent;
import com.xu.xbasketball.di.TestActivityModule;
import com.xu.xbasketball.di.TestAppComponent;
import com.xu.xbasketball.di.component.ActivityComponent;
import com.xu.xbasketball.di.component.AppComponent;
import com.xu.xbasketball.di.component.DaggerActivityComponent;
import com.xu.xbasketball.di.component.DaggerAppComponent;
import com.xu.xbasketball.di.module.ActivityModule;
import com.xu.xbasketball.di.module.AppModule;
import com.xu.xbasketball.di.module.NetModule;
import com.xu.xbasketball.ui.court.activity.CourtDetailActivity;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import org.hamcrest.CustomMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;

/**
 * Created by xu on 2019/04/06.
 *
 * @author xu
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CourtDetailActivityTest extends BaseUITest {

    @Rule
    public ActivityTestRule<CourtDetailActivity> courtDetailActivityActivityTestRule =
            new ActivityTestRule<>(CourtDetailActivity.class, true, false);

    @Override
    public void init() {
        TestApp testApp = (TestApp) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();

//        DaggerTestActivityComponent.builder()
//                .testAppComponent(testApp.getTestAppComponent())
//                .testActivityModule(new TestActivityModule(this))

        Intent intent = new Intent(InstrumentationRegistry.getTargetContext(), CourtDetailActivity.class);
        intent.putExtra(Constants.COURT_URL, TestConstants.COURT_DETAIL_ACTIVITY_TEST_URL);
        courtDetailActivityActivityTestRule.launchActivity(intent);
    }

    @Test
    public void showCourtDetail() {
        onView(withId(R.id.clp_toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.clp_toolbar)).check(matches(withCollapsingToolbarLayoutText(TestConstants.COURT_DETAIL_ACTIVITY_TEST_TITLE)));
    }

    @Override
    public void clear() {

    }

    private Matcher<View> withCollapsingToolbarLayoutText(String matcherText) {
        return new BoundedMatcher<View, CollapsingToolbarLayout>(CollapsingToolbarLayout.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("CollapsingToolbarLayout-text: " + matcherText);
            }

            @Override
            protected boolean matchesSafely(CollapsingToolbarLayout item) {
                return matcherText.contentEquals(item.getTitle());
            }
        };
    }

}
