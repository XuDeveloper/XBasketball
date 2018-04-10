package com.xu.xbasketball.ui.main;

import android.support.v4.app.Fragment;

import com.xu.xbasketball.ui.news.fragment.NewsFragment;
import com.xu.xbasketball.ui.TestFragment2;
import com.xu.xbasketball.ui.dailyscore.fragment.DailyScoreFragment;

import java.util.HashMap;

/**
 * Created by Xu on 2018/4/7.
 *
 * @author Xu
 */
public class FragmentFactory {

    private static HashMap<Integer, Fragment> hashMap = new HashMap<>();

    public static Fragment createFragment(int pos) {
        Fragment fragment = hashMap.get(pos);
        if (fragment == null) {
            switch (pos) {
                case 0:
                    // 今日战报
                    fragment = new DailyScoreFragment();
                    break;
                case 1:
                    fragment = new NewsFragment();
                    break;
                case 2:
                    fragment = new TestFragment2();
                    break;
            }
            hashMap.put(pos, fragment);
        }
        return fragment;
    }

}
