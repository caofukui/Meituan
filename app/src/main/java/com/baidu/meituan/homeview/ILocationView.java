package com.baidu.meituan.homeview;

import com.baidu.meituan.homebean.LocationBeans;

public interface ILocationView {
    void onSuccess(LocationBeans locationBeans);
    void onError(int code);
}
