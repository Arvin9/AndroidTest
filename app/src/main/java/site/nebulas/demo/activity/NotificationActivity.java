package site.nebulas.demo.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import site.nebulas.demo.R;

/**
 * Created by Nebula on 2017/9/23.
 */
public class NotificationActivity extends Activity implements View.OnClickListener{

    NotificationManager notificationManager;
    int NOTIFICATION_ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initView();
    }

    private void initView() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        findViewById(R.id.send).setOnClickListener(this);
        findViewById(R.id.cancle).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send: {
                sendNotification();
                break;
            }
            case R.id.cancle: {
                notificationManager.cancel(NOTIFICATION_ID);
                break;
            }
        }
    }
    /**
     * 构造Notification
     * */
    private void sendNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher_round); // 设置图标
        builder.setTicker("hello"); // 手机状态栏的提示
        builder.setWhen(System.currentTimeMillis()); // 设置时间
        builder.setContentTitle("通知栏通知"); // 设置标题
        builder.setContentText("来自Notification"); //设置通知内容
        builder.setContentIntent(pintent); // 点击之后的意图
//        builder.setDefaults(Notification.DEFAULT_SOUND); // 设置提示声音
//        builder.setDefaults(Notification.DEFAULT_LIGHTS); // 设置指示灯
//        builder.setDefaults(Notification.DEFAULT_VIBRATE); // 设置震动
        builder.setDefaults(Notification.DEFAULT_ALL); // 振动，声音，指示灯
        Notification notification = builder.build();
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
