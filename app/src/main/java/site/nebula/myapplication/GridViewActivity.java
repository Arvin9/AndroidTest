package site.nebula.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nebula on 2017/9/24.
 */

public class GridViewActivity extends Activity {
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

}
