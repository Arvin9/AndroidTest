package site.nebulas.demo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import site.nebulas.demo.R;

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
        findViewById(R.id.grid_view).setOnClickListener(this);
        findViewById(R.id.shared_preferences).setOnClickListener(this);
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
        }
    }
}
