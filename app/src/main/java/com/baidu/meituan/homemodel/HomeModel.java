package com.baidu.meituan.homemodel;

import android.util.Log;

import com.baidu.meituan.homebean.HomeBean;
import com.baidu.meituan.http.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeModel {
    private static final String TAG = HomeModel.class.getSimpleName();
    private String api="http://39.108.3.12:3000/v1/restaurants?offset=";
    private String api2="&limit=3&lng=116.29845&lat=39.95933";
    public void getData(final IhomeModel ihomeModel,int page){
        HttpUtils.doGet(api + page + api2, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                ihomeModel.onError("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                String string = response.body().string();
                HomeBean homeBean = gson.fromJson(string, HomeBean.class);
                ihomeModel.onSuccess(homeBean);
            }
        });

    }
}
