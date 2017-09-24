package site.nebulas.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import site.nebulas.demo.R;

/**
 * Created by Nebula on 2017/9/24.
 */

public class GridViewActivity extends Activity implements AdapterView.OnItemClickListener{
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private int[] icon = {
        R.drawable.ic_alarm_white_48dp, R.drawable.ic_code_white_48dp, R.drawable.ic_credit_card_white_48dp,
        R.drawable.ic_schedule_white_48dp, R.drawable.ic_search_white_48dp, R.drawable.ic_shopping_cart_white_48dp
    };
    private String[] iconName = {
        "闹钟", "代码", "信用卡", "时间", "搜索", "购物车"
    };
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        initView();
    }

    private void initView() {
        gridView = (GridView) findViewById(R.id.grid_view);
        // 1、准备数据源
        dataList = new ArrayList<Map<String, Object>>();

        // 2、新建适配器
        adapter = new SimpleAdapter(this, getData(), R.layout.item_grid_view, new String[]{"image", "name"}, new int[]{R.id.image, R.id.name});

        // 3、Gridview加载适配器
        gridView.setAdapter(adapter);
        // 4、GridView配置监听事件
        gridView.setOnItemClickListener(this);
    }

    private List<Map<String, Object>> getData() {
        for (int i = 0; i < icon.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("image", icon[i]);
            map.put("name", iconName[i]);
            dataList.add(map);
        }
        return dataList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, iconName[position].trim(), Toast.LENGTH_SHORT).show();
    }
}
