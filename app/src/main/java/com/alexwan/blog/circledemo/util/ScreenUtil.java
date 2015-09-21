package com.alexwan.blog.circledemo.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

import com.alexwan.blog.circledemo.help.AppProfile;

/**
 * 屏幕参数工具类
 */
public class ScreenUtil {
    private static final String TAG = ScreenUtil.class.getSimpleName();

    public static int[] getScreenSize(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        if (context instanceof Activity) {
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        }
        int[] screenSize = new int[2];
        screenSize[0] = dm.widthPixels;
        screenSize[1] = dm.heightPixels;
        if (AppProfile.DEBUG) {
            Log.d(TAG, "ScreenWith : " + screenSize[0] + " ScreenHeight : " + screenSize[1]);
        }
        return screenSize;
    }

}
