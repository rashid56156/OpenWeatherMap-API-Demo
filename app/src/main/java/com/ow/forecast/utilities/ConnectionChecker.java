package com.ow.forecast.utilities;

import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectionChecker {

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
