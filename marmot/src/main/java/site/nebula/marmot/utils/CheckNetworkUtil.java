package site.nebula.marmot.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Nebula on 2017/9/30.
 */

public class CheckNetworkUtil {
    private ConnectivityManager connectivityManager;

    public CheckNetworkUtil(Context paramContext) {
        this.connectivityManager = ((ConnectivityManager)paramContext.getSystemService(Context.CONNECTIVITY_SERVICE));
    }

    public int getNetworkType(){
        try{
            NetworkInfo localNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
            if (localNetworkInfo != null)
            {
                int i = localNetworkInfo.getType();
                return i;
            }
        } catch (Exception localException) {
            LogUtil.error("com.zhuge.Connective", "获取网络类型出错", localException);
        }
        return -100;
    }

    public boolean checkNetworkState()
    {
        boolean bool;
        try {
            NetworkInfo localNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
            bool = (localNetworkInfo != null) && (localNetworkInfo.isConnected());
        } catch (Exception localException) {
            bool = true;
            LogUtil.error("com.zhuge.Connective", "", localException);
        }
        return bool;
    }
}
