package com.baidu.meituan.homemodel;

import android.util.Log;

import com.baidu.meituan.homebean.SearchBean;
import com.baidu.meituan.http.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchModel {
    private static final String TAG = SearchModel.class.getSimpleName();
    private String api="http://39.108.3.12:3000/v1/search/restaurant?keyword=";
    public void getDatas(final IsearchModel isearchModel, String keyword){
        HttpUtils.doGet(api + keyword, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                isearchModel.onError("请求失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson=new Gson();
                SearchBean searchBean = gson.fromJson(string, SearchBean.class);
                isearchModel.onSuccess(searchBean);
            }
        });
    }
}
