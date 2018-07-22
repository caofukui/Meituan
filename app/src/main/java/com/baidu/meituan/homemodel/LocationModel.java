package com.baidu.meituan.homemodel;

import com.baidu.meituan.homebean.LocationBeans;
import com.baidu.meituan.http.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LocationModel {
    private String api="http://39.108.3.12:3000/v1/location";
    public void getDatas(final ILocationModel iLocationModel){
        HttpUtils.doGet(api, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iLocationModel.onError(3);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                LocationBeans locationBeans = gson.fromJson(string, LocationBeans.class);
                iLocationModel.onSuccess(locationBeans);
            }
        });
    }
}
