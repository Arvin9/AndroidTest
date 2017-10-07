package site.nebulas.demo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import site.nebulas.demo.R;
import site.nebulas.demo.entity.UserInfo;

/**
 * Created by Nebula on 2017/10/6.
 */

public class UserAdapter extends BaseAdapter {
    private ArrayList<UserInfo> list;
    private Context context;

    public UserAdapter(Context context, ArrayList<UserInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (null == convertView) {
            /**
             * 错误：addView(View, LayoutParams) is not supported in AdapterView
             * 解决：将第三个参数置为null
             * */
            view = View.inflate(context, R.layout.item_listview, null);
        } else {
            view = convertView;
        }
        TextView username = (TextView) view.findViewById(R.id.username);
        TextView password = (TextView) view.findViewById(R.id.password);
        username.setText(list.get(position).getName());
        password.setText(list.get(position).getPassword());
        return view;
    }

}
