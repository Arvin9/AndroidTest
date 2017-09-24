package site.nebula.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nebula on 2017/9/23.
 */
public class CallUpActivity extends AppCompatActivity {
    private TextView phoneNumberTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_up);
        initView();
    }

    private void initView() {
        phoneNumberTv = (TextView) findViewById(R.id.phone_number);
        findViewById(R.id.call_up).setOnClickListener(new CallUp());
    }

    private  class CallUp implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String phoneNumber = phoneNumberTv.getText().toString().trim();
            if (null == phoneNumber || "".equals(phoneNumber)) {
                Toast.makeText(CallUpActivity.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }

}
