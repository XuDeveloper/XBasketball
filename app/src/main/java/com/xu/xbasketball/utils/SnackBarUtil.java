package com.xu.xbasketball.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.xu.xbasketball.R;

/**
 * Created by Xu on 2018/3/20.
 * @author Xu
 */

public class SnackBarUtil {

    /**
     * 显示弹出窗口
     *
     * @param view
     * @param content
     */
    public static void show(View view, String content) {
        final Snackbar snackbar = Snackbar.make(view, content, Snackbar.LENGTH_SHORT);
        View demo = snackbar.getView();
        ((TextView) demo.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }

    /**
     * 显示弹出窗口（长）
     *
     * @param view
     * @param content
     */
    public static void showLong(View view, String content) {
        final Snackbar snackbar = Snackbar.make(view, content, Snackbar.LENGTH_LONG);
        View demo = snackbar.getView();
        ((TextView) demo.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.show();
    }

    /**
     * 显示弹出窗口
     *
     * @param view
     * @param content
     * @param buttonText
     */
    public static void show(View view, String content, String buttonText) {
        final Snackbar snackbar = Snackbar.make(view, content, Snackbar.LENGTH_SHORT);
        View demo = snackbar.getView();
        ((TextView) demo.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.setAction(buttonText, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    /**
     * 显示弹出窗口（长）
     *
     * @param view
     * @param content
     * @param buttonText
     */
    public static void showLong(View view, String content, String buttonText) {
        final Snackbar snackbar = Snackbar.make(view, content, Snackbar.LENGTH_LONG);
        View demo = snackbar.getView();
        ((TextView) demo.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.setAction(buttonText, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

    /**
     * 显示弹出窗口
     *
     * @param view
     * @param content
     * @param buttonText
     * @param listener
     */
    public static void show(View view, String content, String buttonText, View.OnClickListener listener) {
        final Snackbar snackbar = Snackbar.make(view, content, Snackbar.LENGTH_SHORT);
        View demo = snackbar.getView();
        ((TextView) demo.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.setAction(buttonText, listener);
        snackbar.show();
    }

    /**
     * 显示弹出窗口（长）
     *
     * @param view
     * @param content
     * @param buttonText
     * @param listener
     */
    public static void showLong(View view, String content, String buttonText, View.OnClickListener listener) {
        final Snackbar snackbar = Snackbar.make(view, content, Snackbar.LENGTH_LONG);
        View demo = snackbar.getView();
        ((TextView) demo.findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        snackbar.setAction(buttonText, listener);
        snackbar.show();
    }

}
