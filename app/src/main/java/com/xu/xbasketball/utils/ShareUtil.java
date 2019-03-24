package com.xu.xbasketball.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.xu.xbasketball.app.Constants;

/**
 * Created by xu on 2019/03/24.
 *
 * @author xu
 */
public class ShareUtil {

    public static void shareText(Context context, String text, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, title));
    }

    public static void shareImage(Context context, Uri uri, String title) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/png");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, title));
    } 

    public static void sendEmail(Context context, String title) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + Constants.EMAIL_ADDRESS));
        context.startActivity(Intent.createChooser(intent, title));
    }

}
