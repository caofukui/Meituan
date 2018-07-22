package com.baidu.meituan.homemodel;

import com.baidu.meituan.homebean.LocationBeans;

public interface ILocationModel {
    void onSuccess(LocationBeans locationBeans);
    void onError(int code);
}
