package site.nebula.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.call_up).setOnClickListener(this);
        findViewById(R.id.create_alarm).setOnClickListener(this);
        findViewById(R.id.notification).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
        }
    }
}
