package site.nebulas.demo.aplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.tencent.bugly.Bugly;
import com.tencent.mta.track.DebugMode;
import com.tencent.mta.track.StatisticsDataAPI;
import com.tencent.stat.MtaSDkException;
import com.tencent.stat.StatService;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;
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

        //initAli();

        initMTA();

        //talkingData();

        //initBugly();
    }

    private void initAli() {
        /* 【注意】建议您在Application中初始化MAN，以保证正常获取MANService*/
        // 获取MAN服务
        MANService manService = MANServiceProvider.getService();
        // 打开调试日志，线上版本建议关闭
        manService.getMANAnalytics().turnOnDebug();
        // 设置渠道（用以标记该app的分发渠道名称），如果不关心可以不设置即不调用该接口，渠道设置将影响控制台【渠道分析】栏目的报表展现。如果文档3.3章节更能满足您渠道配置的需求，就不要调用此方法，按照3.3进行配置即可；1.1.6版本及之后的版本，请在init方法之前调用此方法设置channel.
        manService.getMANAnalytics().setChannel("测试");
        // MAN初始化方法之一，从AndroidManifest.xml中获取appKey和appSecret初始化
        manService.getMANAnalytics().init(this, getApplicationContext());
        // MAN另一初始化方法，手动指定appKey和appSecret
//        String appKey = "24659757";
//        String appSecret = "e923df7d583fa24eafab347863b8fd84";
//        manService.getMANAnalytics().init(this, getApplicationContext(), appKey, appSecret);
        // 若需要关闭 SDK 的自动异常捕获功能可进行如下操作,详见文档5.4
        //manService.getMANAnalytics().turnOffCrashReporter();
        // 通过此接口关闭页面自动打点功能，详见文档4.2
        manService.getMANAnalytics().turnOffAutoPageTrack();
        // 若AndroidManifest.xml 中的 android:versionName 不能满足需求，可在此指定
        // 若在上述两个地方均没有设置appversion，上报的字段默认为null
        //manService.getMANAnalytics().setAppVersion("3.1.1");
    }

    private void initBugly() {
        //CrashReport.initCrashReport(getApplicationContext(), "fd3d1ea5b2", true);
        Bugly.init(getApplicationContext(), "fd3d1ea5b2", false);
    }

    private void initMTA() {
        // androidManifest.xml指定本activity最先启动
        // 因此，MTA的初始化工作需要在本onCreate中进行
        // 在startStatService之前调用StatConfig配置类接口，使得MTA配置及时生
        String appkey = "ATCD5E9V6E1Z";
        // 初始化并启动MTA
        try {
            // 第三个参数必须为：com.tencent.stat.common.StatConstants.VERSION
            StatService.startStatService(this, appkey,
                    com.tencent.stat.common.StatConstants.VERSION);
            Log.d("MTA","MTA初始化成功");
        } catch (MtaSDkException e) {
        // MTA初始化失败
            Log.d("MTA","MTA初始化失败"+e);
        }

        // 可视化埋点
        StatisticsDataAPI.instance(this, DebugMode.DEBUG_AND_TRACK);
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
        TCAgent.setReportUncaughtExceptions(false);
    }
}
