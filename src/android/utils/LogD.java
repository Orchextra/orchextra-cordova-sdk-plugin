package orchextra.utils;

import android.util.Log;

import org.apache.cordova.BuildConfig;

public class LogD {

    public static final String TAG = LogD.class.getSimpleName();

    public static final boolean ENABLE_LOG = BuildConfig.DEBUG;

    public static void i(String message) {
        if (ENABLE_LOG) {
            Log.i(TAG, message);
        }
    }
}
