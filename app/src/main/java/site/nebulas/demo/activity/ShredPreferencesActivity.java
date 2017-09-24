package site.nebulas.demo.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import site.nebulas.demo.R;

/**
 * Created by Nebula on 2017/9/24.
 */
public class ShredPreferencesActivity extends Activity {
    private TextView showTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shred_preferences);
        initView();
        // SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(ShredPreferencesActivity.this);
        SharedPreferences pref = getSharedPreferences("myPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", "hello");
        editor.commit();

        showTv.setText(pref.getString("name",""));
    }

    private void initView() {
        showTv = (TextView) findViewById(R.id.show_text);
    }

}
