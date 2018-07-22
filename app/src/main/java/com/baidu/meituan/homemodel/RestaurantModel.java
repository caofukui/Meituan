package com.baidu.meituan.homemodel;

import com.baidu.meituan.homebean.RestaurantBean;
import com.baidu.meituan.http.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RestaurantModel {
    private String api="http://39.108.3.12:3000/v1/restaurant/";
    public void getDatas(final IRestaurantModel iRestaurantModel, String page){
        HttpUtils.doGet(api + page, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iRestaurantModel.onError(6);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                RestaurantBean restaurantBean = gson.fromJson(string, RestaurantBean.class);
                iRestaurantModel.onSuccess(restaurantBean);
            }
        });
    }
}
