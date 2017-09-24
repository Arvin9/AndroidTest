package site.nebula.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nebula on 2017/9/23.
 */
public class AlarmActivity extends Activity{
    private EditText hourEt;
    private EditText minuteEt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        initView();
    }

    private void initView() {
        hourEt = (EditText) findViewById(R.id.hour);
        minuteEt = (EditText) findViewById(R.id.minute);
        findViewById(R.id.create_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hour = hourEt.getText().toString().trim();
                String minute = minuteEt.getText().toString().trim();
                if (null != hour && !"".equals(hour) && null != minute && !"".equals(minute)) {
                    createAlarm("闹钟", new Integer(hour).intValue(), new Integer(minute).intValue());
                } else {
                    Toast.makeText(AlarmActivity.this, "小时和分钟不能为空!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}

