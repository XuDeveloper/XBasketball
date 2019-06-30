package com.xu.xbasketball.ui.video;

import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.TestConstants;
import com.xu.xbasketball.ui.video.activity.VideoDetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by xu on 2019/04/14.
 *
 * @author xu
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class VideoDetailActivityTest {

    @Rule
    public ActivityTestRule<VideoDetailActivity> videoDetailActivityActivityTestRule =
            new ActivityTestRule<>(VideoDetailActivity.class, true, false);

    @Before
    public void setUp() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), VideoDetailActivity.class);
        intent.putExtra(Constants.VIDEO_ID, TestConstants.VIDEO_DETAIL_ACTIVITY_TEST_ID);
        intent.putExtra(Constants.VIDEO_TITLE, TestConstants.VIDEO_DETAIL_ACTIVITY_TEST_TITLE);
        videoDetailActivityActivityTestRule.launchActivity(intent);
        IdlingRegistry.getInstance().register(videoDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void showVideoDetail() {
        onView(withId(R.id.tb_basketball)).check(matches(isDisplayed()));
        onView(withId(R.id.fl_video_detail)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().register(videoDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }
}