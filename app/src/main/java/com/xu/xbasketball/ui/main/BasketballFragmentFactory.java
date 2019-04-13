package com.xu.xbasketball.ui.main;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import com.xu.xbasketball.ui.court.fragment.CourtFragment;
import com.xu.xbasketball.ui.news.fragment.NewsFragment;
import com.xu.xbasketball.ui.dailyscore.fragment.DailyScoreFragment;


/**
 * Created by Xu on 2018/4/7.
 *
 * @author Xu
 */
public class BasketballFragmentFactory {

    private static SparseArray<Fragment> sparseArray = new SparseArray<>();

    public static Fragment createFragment(int pos) {
        Fragment fragment = sparseArray.get(pos);
        if (fragment == null) {
            switch (pos) {
                case 0:
                    // 今日战报
                    fragment = new DailyScoreFragment();
                    break;
                case 1:
                    // 篮球新闻
                    fragment = new NewsFragment();
                    break;
                case 2:
                    // 球场中心
                    fragment = new CourtFragment();
                    break;
            }
            sparseArray.put(pos, fragment);
        }
        return fragment;
    }

}
