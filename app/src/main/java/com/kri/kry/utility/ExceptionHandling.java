package com.kri.kry.utility;

import android.util.Log;

/**
 * Created by dj on 16/2/16.
 */
public class ExceptionHandling {

    public static void ProcessException(Exception ex) {

        Log.i("com.kri.kry", ex.getMessage());
        Log.i("com.kri.kry", Log.getStackTraceString(ex));

    }
}
