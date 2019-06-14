package com.xu.xbasketball.ui.news;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.TestConstants;
import com.xu.xbasketball.ui.news.activity.NewsDetailActivity;

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
 * Created by xu on 2019/4/9.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewsDetailActivityTest {

    @Rule
    public ActivityTestRule<NewsDetailActivity> newsDetailActivityActivityTestRule =
            new ActivityTestRule<>(NewsDetailActivity.class, true, false);

    @Before
    public void setUp() {
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), NewsDetailActivity.class);
        intent.putExtra(Constants.NEWS_URL, TestConstants.NEWS_DETAIL_ACTIVITY_TEST_URL);
        intent.putExtra(Constants.NEWS_IMG, TestConstants.NEWS_DETAIL_ACTIVITY_TEST_IMG);
        intent.putExtra(Constants.NEWS_TITLE, TestConstants.NEWS_DETAIL_ACTIVITY_TEST_TITLE);
        newsDetailActivityActivityTestRule.launchActivity(intent);
        IdlingRegistry.getInstance().register(newsDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void showNewsDetail() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_news_detail_pic)).check(matches(isDisplayed()));
//        onView(withId(R.id.fl_news_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.clp_toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.clp_toolbar)).check(matches(withCollapsingToolbarLayoutText(is(TestConstants.NEWS_DETAIL_ACTIVITY_TEST_TITLE))));
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(newsDetailActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }
}