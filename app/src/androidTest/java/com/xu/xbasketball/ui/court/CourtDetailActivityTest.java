package com.xu.xbasketball.ui.court;

import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.TestConstants;
import com.xu.xbasketball.ui.court.activity.CourtDetailActivity;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
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
