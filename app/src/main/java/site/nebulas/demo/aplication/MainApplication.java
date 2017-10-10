package site.nebulas.demo.aplication;

import android.app.Application;
import android.content.Context;
import com.tendcloud.tenddata.TCAgent;

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
        talkingData();
    }

    private void talkingData() {
        TCAgent.LOG_ON = true;
        // App ID: 在TalkingData创建应用后，进入数据报表页中，在“系统设置”-“编辑应用”页面里查看App ID。
        // 渠道 ID: 是渠道标识符，可通过不同渠道单独追踪数据。
        TCAgent.init(this, "C393A948E6F9496D8367AB4159D2FB02", "1");
        // 如果已经在AndroidManifest.xml配置了App ID和渠道ID，调用TCAgent.init(this)即可；或与AndroidManifest.xml中的对应参数保持一致。
        TCAgent.setReportUncaughtExceptions(true);

        // true: 开启自动捕获
        // false: 关闭自动捕获
        TCAgent.setReportUncaughtExceptions(true);
    }
}
