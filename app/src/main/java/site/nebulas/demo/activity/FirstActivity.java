package site.nebulas.demo.activity;

import android.os.Bundle;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;

import site.nebulas.demo.base.BaseActivity;

/**
 * Created by Nebula on 2017/12/6.
 */

public class FirstActivity extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 百度推送
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY,"G3d4LRtyihTQgtVwEAbz1xEU");
    }
}
