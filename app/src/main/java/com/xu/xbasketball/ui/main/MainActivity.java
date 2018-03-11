package com.xu.xbasketball.ui.main;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.xu.xbasketball.R;
import com.xu.xbasketball.model.bean.GameBean;
import com.xu.xbasketball.model.bean.ScoreBoardBean;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initToolbar(toolbar);

        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://matchweb.sports.qq.com/kbs/list?columnId=100000&startTime=2018-03-10&endTime=2018-03-10")
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("test", e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("test", "success");
                String result = response.body().string();
                ScoreBoardBean scoreBoardBean = new Gson().fromJson(result, ScoreBoardBean.class);
                Map<String, List<GameBean>> data = scoreBoardBean.getData();
                List<GameBean> gameBeans = data.get("2018-03-10");
                Log.i("test", gameBeans.get(0).getLeftName());
            }
        });

    }

    private void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
//        mDrawerToggle.syncState();
    }
}
