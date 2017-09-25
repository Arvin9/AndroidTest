package site.nebulas.demo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Nebula on 2017/9/25.
 */

public class BC1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        Log.i("BC1", "接收到的广播:" + msg);
    }
}
