package site.nebula.marmot;

import android.content.Context;
import android.util.Log;

/**
 * Created by Nebula on 2017/9/30.
 */

public class MarmotSDK {
    private static String TAG = "MarmotSDK";
    private boolean initialized = false;



    public static MarmotSDK getInstance() {
        return GetMarmotSDK.marmotSDK;
    }

    // 1、初始化
    public void init(Context paramContext) {
        Log.i(TAG, "初始化");
        if (this.initialized) {
            return;
        }
        this.initialized = true;
    }

    // 2、打开日志、设置日志级别
    public void openLog() {
    }

    public void setLogLevel(int paramInt) {
    }
    // 3、识别用户
    // 4、自定义事件
    // 5、时长统计



    public void openDebug()
    {

    }

    private static class GetMarmotSDK {
        private static final MarmotSDK marmotSDK = new MarmotSDK();
    }
}
