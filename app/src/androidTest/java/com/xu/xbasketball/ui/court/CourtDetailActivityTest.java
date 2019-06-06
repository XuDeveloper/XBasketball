package com.xu.xbasketball.ui.court;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.TestConstants;
import com.xu.xbasketball.ui.court.activity.CourtDetailActivity;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static com.xu.xbasketball.base.MatcherUtil.withCollapsingToolbarLayoutText;
import static org.hamcrest.Matchers.is;

/**
 * Created by xu on 2019/04/06.
 *
 * @author xu
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CourtDetailActivityTest {

    @Rule
    public ActivityTestRule<CourtDetailActivity> courtDetailActivityActivityTestRule =
            new ActivityTestRule<>(CourtDetailActivity.class, true, false);

    @Before
    public void setUp() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), CourtDetailActivity.class);
        intent.putExtra(Constants.COURT_URL, TestConstants.COURT_DETAIL_ACTIVITY_TEST_URL);
        courtDetailActivityActivityTestRule.launchActivity(intent);
        IdlingRegistry.getInstance().register(courtDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void showCourtDetail() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_court_detail_pic)).check(matches(isDisplayed()));
        onView(withId(R.id.fl_court_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.clp_toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.clp_toolbar)).check(matches(withCollapsingToolbarLayoutText(is(TestConstants.COURT_DETAIL_ACTIVITY_TEST_TITLE))));
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(courtDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }



}
