package site.nebulas.demo.utils;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import site.nebulas.demo.base.BaseCallback;

/**
 * Created by Nebula on 2017/10/10.
 */

public class HttpUtils {
    private static final OkHttpClient client = new OkHttpClient();

    public static void get(String url, BaseCallback callback) {
        callback.url = url;
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static void post(String url, String json, BaseCallback callback) {
        callback.url = url;
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
