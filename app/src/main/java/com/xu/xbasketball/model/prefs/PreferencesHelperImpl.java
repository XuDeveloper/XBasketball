package com.xu.xbasketball.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.xu.xbasketball.app.App;
import com.xu.xbasketball.app.Constants;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/6/24.
 *
 * @author Xu
 */
public class PreferencesHelperImpl implements PreferencesHelper {

    private static final boolean DEFAULT_NO_IMAGE = false;

    private static final String SHAREDPREFERENCES_NAME = "xbasketball_sp";

    private SharedPreferences mSp;

    @Inject
    public PreferencesHelperImpl() {
        mSp = App.getInstance().getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    // todo 完成设置界面

    @Override
    public boolean getNoImageState() {
        return mSp.getBoolean(Constants.SP_NO_IMAGE, DEFAULT_NO_IMAGE);
    }

    @Override
    public void setNoImageState(boolean state) {
        mSp.edit().putBoolean(Constants.SP_NO_IMAGE, state).apply();
    }
}
