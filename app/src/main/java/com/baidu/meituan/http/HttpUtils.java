package com.baidu.meituan.http;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtils {
    private static OkHttpClient client=null;
    private static final HttpUtils ourInstance = new HttpUtils();

    private HttpUtils() {}

    public static OkHttpClient getInstance() {
        if (client==null){
            synchronized (HttpUtils.class){
                if (client==null){
                    client=new OkHttpClient();
                }
            }
        }
        return client;
    }

    /**
     * get同步请求
     * @param url
     */
    public static void doGetTong(String url) {
        Request request=new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        try {
            call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * get异步请求
     * url:请求连接
     * 通过callback获取数据
     */
    public static void doGet(String url, Callback callback){
        Request request=new Request.Builder()
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }

    /**
     * post异步请求，发送键值对数据
     */
    public static void doPostMap(String url, Map<String,String> map,Callback callback){
        FormBody.Builder builder=new FormBody.Builder();
        for (String key:map.keySet()){
            builder.add(key,map.get(key));
        }
        Request request=new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }
    /**
     * post异步请求,发送JSON数据
     */
    public static void doPostJson(String url,String json,Callback callback){
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        Request request=new Request.Builder()
                .post(body)
                .url(url)
                .build();
        Call call = getInstance().newCall(request);
        call.enqueue(callback);
    }


}
