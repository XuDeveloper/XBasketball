package com.xu.xbasketball.ui.main;

import android.view.View;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.contrib.NavigationViewActions;
import androidx.test.rule.ActivityTestRule;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.TestConstants;
import com.xu.xbasketball.ui.main.activity.MainActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by xu on 2019/04/14.
 *
 * @author xu
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    @Before
    public void setUp() {

    }

    @Test
    public void showBasketballFragment_Tab_DailyScore() {
        sleep(3000);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_basketball));

        onView(withId(R.id.tl_basketball)).check(matches(isDisplayed()));

        Matcher<View> matcher = allOf(withText(TestConstants.MAIN_ACTIVITY_TEST_BASKETBALL_FRAGMENT_TAB_DAILY_SCORE),
                isDescendantOfA(withId(R.id.tl_basketball)));
        onView(matcher).check(matches(isDisplayed())).perform(click());
        sleep(2000);
        onView(withId(R.id.rv_daily_score)).check(matches(isDisplayed())).perform(swipeUp());
        sleep(2000);
        onView(withId(R.id.fab_daily_score_back_to_top)).check(matches(isDisplayed())).perform(click());
        sleep(1000);
        onView(withId(R.id.fab_daily_score_back_to_top)).check(matches(not(isDisplayed())));
    }

    @Test
    public void showBasketballFragment_Tab_News() {
        sleep(3000);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_basketball));

        onView(withId(R.id.tl_basketball)).check(matches(isDisplayed()));

        Matcher<View> matcher = allOf(withText(TestConstants.MAIN_ACTIVITY_TEST_BASKETBALL_FRAGMENT_TAB_NEWS),
                isDescendantOfA(withId(R.id.tl_basketball)));
        onView(matcher).check(matches(isDisplayed())).perform(click());
        sleep(2000);
        onView(withId(R.id.rv_news)).check(matches(isDisplayed())).perform(swipeUp());
        sleep(2000);
        onView(withId(R.id.fab_news_back_to_top)).check(matches(isDisplayed())).perform(click());
        sleep(1000);
        onView(withId(R.id.fab_news_back_to_top)).check(matches(not(isDisplayed())));
    }

    @Test
    public void showBasketballFragment_Tab_Court() {
        sleep(3000);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_basketball));

        onView(withId(R.id.tl_basketball)).check(matches(isDisplayed()));

        Matcher<View> matcher = allOf(withText(TestConstants.MAIN_ACTIVITY_TEST_BASKETBALL_FRAGMENT_TAB_COURT),
                isDescendantOfA(withId(R.id.tl_basketball)));
        onView(matcher).check(matches(isDisplayed())).perform(click());
        sleep(2000);
        onView(withId(R.id.rv_court)).check(matches(isDisplayed())).perform(swipeUp());
        sleep(2000);
        onView(withId(R.id.fab_court_back_to_top)).check(matches(isDisplayed())).perform(click());
        sleep(1000);
        onView(withId(R.id.fab_court_back_to_top)).check(matches(not(isDisplayed())));
    }

    @Test
    public void showPicFragment() {
        sleep(3000);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_pic));

        sleep(2000);
        onView(withId(R.id.rv_pic)).check(matches(isDisplayed())).perform(swipeUp());
        sleep(2000);
        onView(withId(R.id.fab_pic_back_to_top)).check(matches(isDisplayed())).perform(click());
        sleep(1000);
        onView(withId(R.id.fab_pic_back_to_top)).check(matches(not(isDisplayed())));
    }

    @Test
    public void showVideoFragment() {
        sleep(3000);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_video));

        sleep(2000);
        onView(withId(R.id.rv_video)).check(matches(isDisplayed())).perform(swipeUp());
        sleep(2000);
        onView(withId(R.id.fab_video_back_to_top)).check(matches(isDisplayed())).perform(click());
        sleep(1000);
        onView(withId(R.id.fab_video_back_to_top)).check(matches(not(isDisplayed())));
    }

    @Test
    public void showSettingFragment() {
        sleep(3000);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_setting));

        onView(withId(R.id.switch_auto_cache)).check(matches(isDisplayed()));
        onView(withId(R.id.switch_auto_cache)).perform(ViewActions.click()).check(matches(isChecked()));
        onView(withId(R.id.switch_auto_cache)).perform(ViewActions.click()).check(matches(isNotChecked()));
    }

    @Test
    public void showAboutFragment() {
        sleep(3000);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_about));

        onView(withId(R.id.civ_about_img)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_about_text)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {

    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}