package site.nebula.marmot.utils;

import android.util.Log;

/**
 * Created by Nebula on 2017/9/30.
 */

public class LogUtil {
    public static boolean isEnable = false;
    private static int logLevel = Log.INFO; // 默认日志级别

    public static void setLogLevel(int paramInt) {
        if (paramInt > Log.ERROR) {
            logLevel = Log.ERROR;
            return;
        }
        if (paramInt < Log.VERBOSE) {
            logLevel = Log.VERBOSE;
            return;
        }
        logLevel = paramInt;
    }

    public static void openLog() {
        isEnable = true;
    }


    public static void error(String paramString1, String paramString2) {
        Log.e(paramString1, paramString2);
    }

    public static void record(String paramString1, String paramString2) {
        if (!isEnable) {
            return;
        }
        switch (logLevel) {
            case 6:
                Log.e(paramString1, paramString2);
                break;
            case 5:
                Log.w(paramString1, paramString2);
                break;
            case 4:
                Log.i(paramString1, paramString2);
                break;
            case 3:
                Log.d(paramString1, paramString2);
                break;
            case 2:
                Log.v(paramString1, paramString2);
                break;
            default:
                Log.i(paramString1, paramString2);
        }
    }

    public static void verbose(String paramString) {
        if (!isEnable) {
            return;
        }
        Log.v("ZhugeLog", paramString);
    }

    public static void error(String paramString1, String paramString2, Throwable paramThrowable) {
        Log.e(paramString1, paramString2, paramThrowable);
    }
}
