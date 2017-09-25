package site.nebulas.demo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import site.nebulas.demo.R;

/**
 * 广播.
 * Created by Nebula on 2017/9/25.
 *
 */
public class BroadActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
        initView();
    }

    private void initView() {
        findViewById(R.id.send_broad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("msg", "普通广播");
                intent.setAction("BC_One");
                sendBroadcast(intent);
            }
        });
    }
}
