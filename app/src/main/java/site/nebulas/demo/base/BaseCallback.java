package site.nebulas.demo.base;

import android.util.Log;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

/**
 * Created by Nebula on 2017/10/26.
 */

public class BaseCallback implements Callback {
    private String TAG = "BaseCallback";

    public String url;

    @Override
    public void onFailure(Call call, IOException e) {
        Log.i(TAG, "url: " + url);
        Log.i(TAG, "请求失败:" + e.toString());
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        Log.i(TAG, "url: " + url);
        Log.i(TAG, "请求成功: ");
    }
}
