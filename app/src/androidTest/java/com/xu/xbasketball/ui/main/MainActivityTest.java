package com.xu.xbasketball.ui.main;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.LayoutMatchers;
import android.support.test.rule.ActivityTestRule;

import com.xu.xbasketball.R;
import com.xu.xbasketball.ui.main.activity.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static com.xu.xbasketball.base.MatcherUtil.withRecyclerView;

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
    public void showBasketballFragment() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_basketball));

        onView(withId(R.id.tl_basketball)).check(matches(isDisplayed()));
    }

    @Test
    public void showPicFragment() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_pic));

        onView(withId(R.id.rv_pic)).check(matches(isDisplayed()));
    }

    @Test
    public void showVideoFragment() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()));

        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        onView(withId(R.id.navigation)).perform(NavigationViewActions.navigateTo(R.id.navigation_video));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withRecyclerView(R.id.rv_video).atPosition(3)).check(matches(isDisplayed())).perform(RecyclerViewActions.scrollToPosition(3));
        onView(withId(R.id.fab_video_back_to_top)).check(matches(isDisplayed()));
    }

    @Test
    public void showSettingFragment() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}