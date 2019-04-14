package com.xu.xbasketball.ui.pic;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.TestConstants;
import com.xu.xbasketball.ui.pic.activity.PicDetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by xu on 2019/4/14.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PicDetailActivityTest {

    @Rule
    public ActivityTestRule<PicDetailActivity> picDetailActivityActivityTestRule =
            new ActivityTestRule<>(PicDetailActivity.class, true, false);

    @Before
    public void setUp() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), PicDetailActivity.class);
        intent.putExtra(Constants.PIC_URL, TestConstants.PIC_DETAIL_ACTIVITY_TEST_IMG);
        picDetailActivityActivityTestRule.launchActivity(intent);
        IdlingRegistry.getInstance().register(picDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void showPicDetail() {
        onView(withId(R.id.tb_basketball)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_pic_detail)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(picDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }
}