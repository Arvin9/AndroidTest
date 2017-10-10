package site.nebulas.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import site.nebulas.demo.R;
import site.nebulas.demo.utils.HttpUtils;

/**
 * Created by Nebula on 2017/10/10.
 */

public class OkHttpActivity extends Activity implements View.OnClickListener{

    private TextView mTvGetShow;
    private TextView mTvPostShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
    }

    private void initView() {
        findViewById(R.id.get).setOnClickListener(this);
        findViewById(R.id.post).setOnClickListener(this);

        mTvGetShow = (TextView) findViewById(R.id.get_show);
        mTvPostShow = (TextView) findViewById(R.id.post_show);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get: {
                HttpUtils.get("http://publicobject.com/helloworld.txt", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("http", "e:" + e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String result = response.body().string();
                        Log.i("http", "re" + result);
                        Message message = new Message();
                        message.obj = result;
                        handler.sendMessage(message);
                    }
                });
                break;
            }
            case R.id.post: {

                break;
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTvGetShow.setText(msg.obj.toString());
        }
    };
}
