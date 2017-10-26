package site.nebulas.demo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.man.MANService;
import com.alibaba.sdk.android.man.MANServiceProvider;
import com.baidu.mobstat.StatService;
import com.tendcloud.tenddata.TCAgent;
import com.tendcloud.tenddata.TDAccount;
import com.zhuge.analysis.stat.ZhugeSDK;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Response;
import site.nebula.marmot.utils.CheckNetworkUtil;
import site.nebula.marmot.utils.LogUtil;
import site.nebulas.demo.R;
import site.nebulas.demo.base.BaseActivity;
import site.nebulas.demo.base.BaseCallback;
import site.nebulas.demo.utils.HttpUtils;
import site.nebulas.demo.utils.ToastUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private String TAG = "MainActivity";

    private IntentFilter intentFilter;
    private NetworkChangeReceive networkChangeReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TCAgent.onLogin("1234567", TDAccount.AccountType.WEIXIN, "Sun");

        initAli();

        baiduMobAd();

        //initZhugeIO();

        initView();

        networkMonitor();

        checkVersion();
    }

    private void checkVersion() {
        HttpUtils.get("https://raw.githubusercontent.com/Arvin9/data/master/app_version.json", new BaseCallback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                super.onResponse(call, response);
                String result = response.body().string();
                JSONObject body = JSON.parseObject(result);
                int versionCode = body.getIntValue("versionCode");
                Log.i(TAG, "versionCode: " + versionCode);
                compareVsersion(versionCode);
            }
        });
    }

    private void compareVsersion(int code) {
        int versionCode = getVersionCode();
        Log.i(TAG, "versionCode: " +  versionCode);
        if (versionCode < code) {
            // 下载
            Log.i(TAG, "下载");
        }
    }

    private void initAli() {
        MANService manService = MANServiceProvider.getService();
        // 用户登录埋点
        manService.getMANAnalytics().updateUserAccount("usernick", "123456");
    }

    private void initZhugeIO() {
        //初始化分析跟踪
        ZhugeSDK.getInstance().init(getApplicationContext());
        Log.i("zhuge", "诸葛io初始化");
    }

    private void baiduMobAd() {
        // setSendLogStrategy已经@deprecated，建议使用新的start接口
        // 如果没有页面和自定义事件统计埋点，此代码一定要设置，否则无法完成统计
        // 进程第一次执行此代码，会导致发送上次缓存的统计数据；若无上次缓存数据，则发送空启动日志
        // 由于多进程等可能造成Application多次执行，建议此代码不要埋点在Application中，否则可能造    成启动次数偏高
        // 建议此代码埋点在统计路径触发的第一个页面中，若可能存在多个则建议都埋点
        StatService.start(this);
        // 获取测试设备ID
        String testDeviceId = StatService.getTestDeviceId(this);
        // 日志输出
        android.util.Log.d("BaiduMobStat", "Test DeviceId : " + testDeviceId);
    }

    /**
     * 监听网络状态广播
     * */
    private void networkMonitor() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceive = new NetworkChangeReceive();
        registerReceiver(networkChangeReceive, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceive);
    }

    private void initView() {
        findViewById(R.id.call_up).setOnClickListener(this);
        findViewById(R.id.create_alarm).setOnClickListener(this);
        findViewById(R.id.notification).setOnClickListener(this);
        findViewById(R.id.grid_view).setOnClickListener(this);
        findViewById(R.id.shared_preferences).setOnClickListener(this);
        findViewById(R.id.broad).setOnClickListener(this);
        findViewById(R.id.shake).setOnClickListener(this);
        findViewById(R.id.progressbar).setOnClickListener(this);
        findViewById(R.id.check_network_state).setOnClickListener(this);
        findViewById(R.id.sqlite).setOnClickListener(this);
        findViewById(R.id.okhttp).setOnClickListener(this);
        findViewById(R.id.camera).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_network_state: {
                CheckNetworkUtil checkNetworkUtil = new CheckNetworkUtil(this);
                boolean networkState = checkNetworkUtil.checkNetworkState();
                int networkType = checkNetworkUtil.getNetworkType();
                LogUtil.openLog();
                LogUtil.record("Main", "网络状态："+ networkState);
                LogUtil.record("Main", "网络类型："+ networkType);
                ToastUtil.toast("网络状态："+ networkState);
                ToastUtil.toast("网络类型："+ networkType);
                break;
            }
            case R.id.call_up: {
                Toast.makeText(this, "打电话", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, CallUpActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.create_alarm: {
                Toast.makeText(this, "设闹钟", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AlarmActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.notification: {
                Toast.makeText(this, "通知", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.grid_view: {
                Toast.makeText(this, "GridView", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, GridViewActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.shared_preferences: {
                Toast.makeText(this, "ShredPreferences", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ShredPreferencesActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.broad: {
                Toast.makeText(this, "广播", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, BroadActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.shake: {
                Toast.makeText(this, "摇一摇", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ShakeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.progressbar: {
                Toast.makeText(this, "进度条", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ProgressbarActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.sqlite: {
                Toast.makeText(this, "SQLite", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, SQLiteActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.okhttp: {
                ToastUtil.toast("okHttp");
                Intent intent = new Intent(this, OkHttpActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.camera: {
                ToastUtil.toast("摄像头");
                Intent intent = new Intent(this, CameraActivity.class);
                startActivity(intent);
                break;
            }
        }
    }




    class NetworkChangeReceive extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                ToastUtil.toast("网络正常");
            } else {
                ToastUtil.toast("当前网络不可用");
            }

        }
    }
}
