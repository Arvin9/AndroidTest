package site.nebulas.demo.aplication;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nebula on 2017/9/30.
 */

public class MainApplication extends Application{
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
